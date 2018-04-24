package com.scloud.exception;

import lombok.Data;

/**
 *
 * Created by andy on 2018/4/23.
 */
@Data
public class ErrorInfo<T> {

    public static final Integer OK = 0;
    public static final Integer ERROR = 100;

    private Integer code;
    private String message;
    private String url;
    private T data;







}
