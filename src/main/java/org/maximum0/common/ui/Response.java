package org.maximum0.common.ui;

import org.maximum0.common.domain.exception.ErrorCode;

public record Response<T>(
        Integer code,
        String message,
        T data
) {
    public static <T> Response<T> ok(T data) {
        return new Response<>(0, "ok", data);
    }

    public static <T> Response<T> fail(ErrorCode error) {
        return new Response<>(error.getCode(), error.getMessage(), null);
    }
}
