package com.pharmeasy.admin.common.handler.response;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public final class ResponseUtil {

    private static final Map data = new HashMap();
    private static ResponseUtil responseUtil;

    public ResponseDTO ok() {
        String message = ApiResponseCode.SUCCESS.getMessage();
        return new ApiResponseDTO(message, data);
    }

    public ResponseDTO ok(Object data) {
        String message = ApiResponseCode.SUCCESS.getMessage();
        return new ApiResponseDTO(message, data);
    }

    public ResponseDTO exception(int code, String message) {
        return new ApiResponseDTO(code, message, data);
    }

    public ResponseDTO exception(ResponseCode responseCode) {
        String message = responseCode.getMessage();
        return new ApiResponseDTO(responseCode.getCode(), message, data);
    }
    public ResponseDTO validationFailed(int code, String message) {
        return new ApiResponseDTO(code, message, data);
    }
}
