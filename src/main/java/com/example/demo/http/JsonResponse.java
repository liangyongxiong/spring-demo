package com.example.demo.http;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JsonResponse {
    private Integer err;
    private String msg;
    private Object data;
    private String log;

    public JsonResponse() {
        this(null, null, null, null);
    }

    public JsonResponse(Integer err, String msg, Object data, String log) {
        this.err = err == null ? 0 : err;
        this.msg = msg == null ? "" : msg;
        this.data = data;
        this.log = log == null ? "" : log;
    }
}

