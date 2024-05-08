package com.dazecake.ocjave.utils;

import com.dazecake.ocjave.constant.ResponseCodeConstants;
import com.google.gson.GsonBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "响应信息主体")
public class Result<T> implements Serializable {

    @Schema(title = "状态码")
    private Integer code;

    @Schema(title = "消息")
    private String msg;

    @Schema(title = "数据", nullable = true)
    private T data;

    public static <T> Result<T> set(int code, String msg, T data) {
        return new Result<>(ResponseCodeConstants.SUCCESS, null, null);
    }

    public static <T> Result<T> success() {
        return new Result<>(ResponseCodeConstants.SUCCESS, null, null);
    }

    public static <T> Result<T> success(String msg) {
        return new Result<>(ResponseCodeConstants.SUCCESS, msg, null);
    }

    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(ResponseCodeConstants.SUCCESS, msg, data);
    }

    public static <T> Result<T> unauthorized(String msg) {
        return new Result<>(ResponseCodeConstants.UNAUTHORIZED, msg, null);
    }

    public static <T> Result<T> forbidden(String msg) {
        return new Result<>(ResponseCodeConstants.FORBIDDEN, msg, null);
    }

    public static <T> Result<T> notfound(String msg) {
        return new Result<>(ResponseCodeConstants.NOT_FOUND, msg, null);
    }

    public static <T> Result<T> badRequest(String msg) {
        return new Result<>(ResponseCodeConstants.PARAM_ERROR, msg, null);
    }

    public static <T> Result<T> fail() {
        return new Result<>(ResponseCodeConstants.FAIL, null, null);
    }

    public static <T> Result<T> fail(String msg) {
        return new Result<>(ResponseCodeConstants.FAIL, msg, null);
    }

    public String toJson() {
        return new GsonBuilder().serializeNulls().create().toJson(this);
    }
}



