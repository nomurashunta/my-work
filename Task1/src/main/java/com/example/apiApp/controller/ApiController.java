package com.example.apiApp.controller;

import com.example.apiApp.domain.service.RepositoryService;
import com.example.common.model.Item;
import com.example.common.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * HTTPリクエストを受け取り、処理結果をJson形式のレスポンスで返すControllerクラスです。
 */
@RestController
@Validated
public class ApiController {

    @Autowired
    RepositoryService repositoryService;

    String maxPriceStr = String.valueOf(Constants.MAX_PRICE);


    /**
     * URIにIDを含める場合のGETメソッドを処理します。
     * @param id 商品のIDです。
     * @return Json形式のレスポンスです。
     */
    @RequestMapping(value = "/api/items/{id}", method = RequestMethod.GET)
    public String getItem(@PathVariable("id") String id)
    {
        return repositoryService.getItemByID(id);
    }

    /**
     * URIにリクエストパラメータを指定する場合のGETメソッドを処理します。
     * パラメーターが不正なときは400エラーを返します。
     * @param id 商品のIDです。省略可能です。
     * @param title 商品のタイトルです。省略可能です。
     * @param description 商品の説明文です。省略可能です。
     * @param maxPrice 商品価格の上限です。省略可能です。
     * @param minPrice 商品価格の下限です。省略可能です。
     * @return Json形式のレスポンスです。
     */
    @RequestMapping(value = "/api/items", method = RequestMethod.GET)
    public String getItemsByQuery(@Valid @RequestParam(value = "id", required = false, defaultValue = "%") String id,
                                  @Valid @RequestParam(value = "title", required = false, defaultValue = "%") String title,
                                  @Valid @RequestParam(value = "description", required = false, defaultValue = "%") String description,
                                  @Valid @Pattern(regexp = "^[0-9]*$") @RequestParam(value = "maxPrice", required = false, defaultValue = Constants.MAX_PRICE_STR) String  maxPrice,
                                  @Valid @Pattern(regexp = "^[0-9]*$") @RequestParam(value = "minPrice", required = false, defaultValue = "0") String minPrice){
        return repositoryService.getItemsByQuery(id, title, description, Integer.valueOf(maxPrice), Integer.valueOf(minPrice));
    }

    /**
     * DELETEメソッドを処理します。
     * @param id 削除対象の商品のIDです。
     * @return Json形式のレスポンスです。
     */
    @RequestMapping(value = "/api/items/{id}", method = RequestMethod.DELETE)
    public String deleteItem(@PathVariable("id") String id){
        return repositoryService.deleteItemByID(id);
    }

    /**
     * PUTメソッドを処理します。
     * @param id 編集対象の商品のIDです。
     * @param item 編集後の商品データです。
     * @return Json形式のレスポンスです。
     */
    @RequestMapping(value = "/api/items/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String editItem(@PathVariable("id") String id, @RequestBody Item item){
        return repositoryService.editItemByID(id, item);
    }

    /**
     * POSTメソッドを処理します。
     * @param item 追加する商品データです。
     * @return Json形式のレスポンスです。
     */
    @RequestMapping(value = "/api/items", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Valid
    public String addItem(@Valid @RequestBody Item item){
        return repositoryService.addItem(item);
    }

}
