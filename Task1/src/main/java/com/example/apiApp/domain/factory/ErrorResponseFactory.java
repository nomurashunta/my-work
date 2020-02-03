package com.example.apiApp.domain.factory;

/**
 * エラーメッセージを生成するためのクラスです。
 */
public class ErrorResponseFactory {

    ResponseFactory responseFactory;

    /**
     * コンストラクタ
     */
    public ErrorResponseFactory(){
        responseFactory = new ResponseFactory();
    }

    /**
     * URLが不正であることを示すエラーメッセージを返します。
     * @return Json形式のレスポンスです。
     */
    public String getPageNotFoundError() {
        return responseFactory.getErrorResponse("404", "URLが不正です");
    }

    /**
     * IDの入力がないことを示すエラーメッセージを返します。
     * @return Json形式のレスポンスです。
     */
    public String getIDIsNullOrEmptyMessage(){
        return responseFactory.getErrorResponse("400","IDの入力がありません");
    }

    /**
     * 指定されたIDをもつ商品が存在しないことを示すエラーメッセージを返します。
     * @param id 商品のIDです。
     * @return Json形式のレスポンスです。
     */
    public String getItemNotFoundMessage(String id){
        return responseFactory.getErrorResponse("404","指定されたIDの商品は存在しません");
    }

    /**
     * PUTメソッド処理時に、指定されたIDを持つ商品が既に存在することを示すエラーメッセージを返します。
     * @param id 商品のIDです。
     * @return Json形式のレスポンスです。
     */
    public String getIDExistsMessage(String id){
        return responseFactory.getErrorResponse("400","指定されたIDの商品が既に存在します");
    }

    /**
     * 何らかの理由でDELETEメソッドが失敗したことを示すエラーメッセージを返します。
     * @param id 商品のIDです。
     * @return Json形式のレスポンスです。
     */
    public String getDeleteFailedMessage(String id){
        return responseFactory.getErrorResponse("500", "商品の削除に失敗しました");
    }

    /**
     * 何らかの理由でPOSTメソッドが失敗したことを示すエラーメッセージを返します。
     * @param id 商品のIDです。
     * @return Json形式のレスポンスです。
     */
    public String getPostFailedMessage(String id){
        return responseFactory.getErrorResponse("500", "商品の追加に失敗しました");
    }

    /**
     * リクエストパラメータに不正があることを示すエラーメッセージを返します。
     * @return Json形式のレスポンスです。
     */
    public String getParamInvalidMessage(){
        return responseFactory.getErrorResponse("400", "リクエストパラメーターが不正です");
    }

    /**
     * 商品データに不正があることを示すエラーメッセージを返します。
     * @return Json形式のレスポンスです。
     */
    public String getItemInvalidMessage(){
        return responseFactory.getErrorResponse("400", "商品データが不正です");
    }


    /**
     * POSTされたJson形式のデータに不正があることを示すエラーメッセージを返します。
     * @return Json形式のレスポンスです。
     */
    public String getJsonFormatInvalidMessage(){
        return responseFactory.getErrorResponse("400", "データの形式が不正です");
    }

    /**
     * JWTトークンに不正があることを示すエラーメッセージを返します。
     * @return Json形式のレスポンスです。
     */
    public String getJWTTokenInvalidMessage(){
        return responseFactory.getErrorResponse("400", "認証トークンが不正です");
    }

}
