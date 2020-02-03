package com.example.apiApp.controller.handler;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.apiApp.domain.factory.ErrorResponseFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;

/**
 * エラーハンドリングを行うクラスです。
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

    private ErrorResponseFactory errorResponseFactory = new ErrorResponseFactory();

    /**
     * ConstraintViolationException発生時のエラー内容を返します。
     * リクエストパラメータに不正があるときに呼ばれます。
     * @param ex
     * @param request
     * @return レスポンス
     */
    @ExceptionHandler
    public String handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        String response = errorResponseFactory.getParamInvalidMessage();
        return response;
    }

    /**
     * HttpMessageNotReadableException発生時のエラー内容を返します。
     * リクエスト時のJSONデータに不正があるときに呼ばれます。
     * @param ex
     * @param request
     * @return レスポンス
     */
    @ExceptionHandler
    public String handleHttpMessageNotReadableException(HttpMessageNotReadableException ex,
                                                                        WebRequest request){
        String response = errorResponseFactory.getJsonFormatInvalidMessage();
        return response;
    }

    /**
     * MethodArgumentNotValidException発生時のエラー内容を返します。
     * 商品データに不正があるときに呼ばれます。
     * @param ex
     * @param request
     * @return レスポンス
     */
    @ExceptionHandler
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                        WebRequest request){
        String response = errorResponseFactory.getItemInvalidMessage();
        return response;
    }

    /**
     * JWTVerificationException発生時のエラー内容を返します。
     * JWTトークンに不正があるときに呼ばれます。
     * @param ex
     * @param request
     * @return レスポンス
     */
    @ExceptionHandler
    public String handleJWTVerificationException(JWTVerificationException ex,
                                                 WebRequest request){
        String response = errorResponseFactory.getJWTTokenInvalidMessage();
        return response;
    }

    /**
     * IllegalArgumentException発生時のエラー内容を返します。
     * JWTトークンに不正があるときに呼ばれます。
     * @param ex
     * @param request
     * @return レスポンス
     */
    @ExceptionHandler
    public String handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request){
        String response = errorResponseFactory.getJWTTokenInvalidMessage();
        return response;
    }

    /**
     * NoHandlerFoundException発生時のエラー内容を返します。
     * リクエスト時のURLが正しくない場合などに呼ばれます。
     * @param ex
     * @param request
     * @return レスポンス
     */
    @ExceptionHandler
    public String handleResourceNotFoundeException(NoHandlerFoundException ex, WebRequest request){
        String response = errorResponseFactory.getPageNotFoundError();
        return response;
    }

}
