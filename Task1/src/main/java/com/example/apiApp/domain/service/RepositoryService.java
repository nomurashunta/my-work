package com.example.apiApp.domain.service;

import com.example.apiApp.domain.factory.ErrorResponseFactory;
import com.example.apiApp.domain.factory.ResponseFactory;
import com.example.common.model.Item;
import com.example.common.repository.ItemRepository;
import com.example.common.utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

/**
 * 商品のRepositoryを操作し、処理結果をJson形式のレスポンスで返すクラスです。
 */
@Service
public class RepositoryService {

    @Autowired
    ItemRepository itemRepository;

    ResponseFactory responseFactory = new ResponseFactory();
    ErrorResponseFactory errorResponseFactory = new ErrorResponseFactory();

    /**
     * 指定されたIDをもつ商品を取得し、Entityオブジェクトを返します。
     * @param id 商品のIDを指定します。完全一致で検索されます。
     * @return Entityオブジェクトです。
     */
    public Item getItemObjectByID(String id){
        if (!itemRepository.existsById(id)) return null;
        else {
            Item item = itemRepository.getOne(id);
            return item;
        }
    }

    /**
     * 指定されたIDをもつ商品を取得し、レスポンスを返します。
     * @param id 商品のIDを指定します。完全一致で検索されます。
     * @return Json形式のレスポンスです。
     */
    public String getItemByID(String id){
        if (!itemRepository.existsById(id)) return errorResponseFactory.getItemNotFoundMessage(id);
        Item item = itemRepository.getOne(id);
        return responseFactory.getGetResponse(item);
    }

    /**
     * 指定された条件で商品の検索を行い、レスポンスを返します。
     * @param id 商品のIDを指定します。部分一致で検索されます。
     * @param title 商品のタイトルを指定します。部分一致で検索されます。
     * @param description 商品の説明文を指定します。部分一致で検索されます。
     * @param maxPrice 商品価格の上限を指定します。
     * @param minPrice 商品価格の下限を指定します。
     * @return Json形式のレスポンスです。
     */
    public String getItemsByQuery(String id, String title, String description, int maxPrice, int minPrice){
        List<Item> items = itemRepository.findByPostedQuery(id, title, description, maxPrice, minPrice);
        return responseFactory.getGetResponse(items);
    }

    /**
     * 指定されたIDをもつ商品を削除し、レスポンスを返します。存在しない場合はステータスコード404が返ります。
     * @param id 商品のIDを指定します。完全一致で検索されます。
     * @return Json形式のレスポンスです。
     */
    public String deleteItemByID(String id){
        if (Utilities.isNullOrEmpty(id)) return errorResponseFactory.getIDIsNullOrEmptyMessage();
        if (!itemRepository.existsById(id)) return errorResponseFactory.getItemNotFoundMessage(id);
        try{
            itemRepository.deleteById(id);
        }
        catch (Exception e){
            return errorResponseFactory.getDeleteFailedMessage(id);
        }
        return responseFactory.getResultResponse(id, "200", "商品を削除しました");
    }

    /**
     * 指定されたIDをもつ商品の編集を行い、レスポンスを返します。存在しない場合はステータスコード404が返ります。
     * @param id 商品のIDを指定します。完全一致で検索されます。
     * @param tmpItem 編集後の商品データが入ったEntityオブジェクトです。
     * @return Json形式のレスポンスです。
     */
    public String editItemByID(String id, Item tmpItem){
        if (tmpItem == null) return errorResponseFactory.getItemInvalidMessage();
        Item item = itemRepository.getOne(id);
        tmpItem.setId(item.getId());
        if (!isItemValid(tmpItem)) return errorResponseFactory.getItemInvalidMessage();
        if (Utilities.isNullOrEmpty(id)) return errorResponseFactory.getIDIsNullOrEmptyMessage();
        if (!itemRepository.existsById(id)) return errorResponseFactory.getItemNotFoundMessage(id);
        item.edit(tmpItem);
        itemRepository.save(item);
        return responseFactory.getResultResponse(id, "200", "商品を編集しました");
    }

    /**
     * 商品の追加を行い、レスポンスを返します。
     * @param item 追加したい商品データが入ったEntityオブジェクトです。
     * @return Json形式のレスポンスです。
     */
    public String addItem(Item item){
        if (!isItemValid(item)) return errorResponseFactory.getItemInvalidMessage();
        String id = item.getId();
        if (itemRepository.existsById(id)) return errorResponseFactory.getIDExistsMessage(id);
        try {
            itemRepository.save(item);
        }catch (Exception e){
             errorResponseFactory.getPostFailedMessage(id);
        }
        return responseFactory.getResultResponse(id, "200", "商品を追加しました");
    }

    /**
     * 商品データのバリデーションを行い、データに不正がなければtrueを返します。
     * @param item 商品データのEntityオブジェクトです。
     * @return データに不正がなければtrueが返ります。
     */
    public boolean isItemValid(Item item){
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Item>> violations = validator.validate(item);
        if (violations.size() == 0) return true;
        return false;
    }

}
