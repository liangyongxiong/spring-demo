package com.example.demo.exception;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
public class BizException extends RuntimeException {
    private String msg;

    public BizException(String msg) {
        this.msg = msg;
    }
}
