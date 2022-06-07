
package com.pharmeasy.admin.common.handler.response;


@SuppressWarnings("rawtypes")
public interface ResponseDTO<T> {

    int getCode();

    void setCode(int code);

    String getMessage();

    ResponseDTO setMessage(String message);

    T getData();

    void setData(T data);
}
