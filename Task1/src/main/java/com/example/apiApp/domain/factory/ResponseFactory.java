package com.example.apiApp.domain.factory;
import com.example.common.model.Item;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * リクエストを受け取った際のレスポンス内容をJson形式で作成するクラスです。
 */
@Component
public class ResponseFactory {

    ObjectMapper objectMapper = new ObjectMapper();

    @Data
    abstract class Response {
        public String status = "200";
        public String message = "";
    }

    @Data
    class ErrorResponse extends Response {
        ErrorResponse(String status, String message){
            this.status = status;
            this.message = message;
        }
    }

    @Data
    class ResultResponse extends Response {
        String id;
        ResultResponse(String id, String status, String message){
            this.status = status;
            this.message = message;
            this.id = id;
        }
    }

    @Data
    class ItemGetResponse extends Response {
        private Item item;
        ItemGetResponse(Item item){
            this.item = item;
        }
    }

    @Data
    class ItemListGetResponse extends Response {
        private List<Item> items;
        ItemListGetResponse(List<Item> items){
            this.items = items;
        }
    }

    /**
     * IDが完全一致した商品データを含むレスポンスを生成し、返します。
     * URIに商品のIDを含むGETメソッド処理時に呼ばれます。
     * @param item レスポンスに含む商品データのEntityオブジェクトです。
     * @return Json形式のレスポンスです。
     */
    public String getGetResponse(Item item){
        Response response = new ItemGetResponse(item);
        String message;
        if (item == null) message = "指定されたIDの商品はありません";
        else message = "ID:" + item.getId() + "の商品です";
        response.setMessage(message);
        return getSerializedResponse(response);
    }

    /**
     * 条件検索で一致した商品データの一覧を含むレスポンスを生成し、返します。
     * パラメータを指定したGETメソッド処理時に呼ばれます。
     * @param items レスポンスに含む商品データのリストです。
     * @return Json形式のレスポンスです。
     */
    public String getGetResponse(List<Item> items){
        Response response = new ItemListGetResponse(items);
        String message = items.size() + "個の商品が見つかりました";
        response.setMessage(message);
        return getSerializedResponse(response);
    }

    /**
     * POST, PUT, DELETEメソッド処理時に呼ばれ、処理結果のレスポンスを生成し、返します。
     * @param id 商品のIDです。
     * @param status ステータスコードです。
     * @param message 処理結果のメッセージです。
     * @return Json形式のレスポンスです。
     */
    public String getResultResponse(String id, String status, String message){
        Response response = new ResultResponse(id, status, message);
        return getSerializedResponse(response);
    }

    /**
     * コントローラー側でハンドリングされたエラーの内容を返します。
     * @param status ステータスコードです。
     * @param message エラーメッセージです。
     * @return Json形式のレスポンスです。
     */
    public String getErrorResponse(String status, String message){
        return getSerializedResponse(new ErrorResponse(status, message));
    }

    /**
     * レスポンス内容を表すResponseクラスのオブジェクトを、Json形式にシリアライズして返します。
     * @param response Responseクラスのオブジェクトです。
     * @return Json形式にシリアライズされたレスポンス内容です。
     */
    public String getSerializedResponse(Response response){
        try {
            String result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
            return result;
        } catch (JsonProcessingException e){
            e.printStackTrace();
            return getSerializeFailedMessage();
        }
    }

    /**
     * 何らかの理由でシリアライズが失敗した場合に呼ばれ、エラーメッセージを返します。
     * @return
     */
    private String getSerializeFailedMessage(){
        String error500 = "{ \"status\" : 500  \"message\" : 不明なエラーです }";
        return error500;
    }
}
