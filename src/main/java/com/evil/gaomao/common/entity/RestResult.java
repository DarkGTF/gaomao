package com.evil.gaomao.common.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.evil.gaomao.common.constant.ResponseCode;
import lombok.Data;

/**
 * 统一响应体
 *
 * @author fangjiaxiaobai@gmail.com
 * @since 1.0.0
 * @date 2019-12-25
 */
@Data
public class RestResult<T> {

    /**
     * 响应状态
     */
    private boolean succeed = false;

    /**
     * 响应码
     */
    private Integer code = ResponseCode.OK;

    /**
     * 响应数据
     */
    @JSONField
    private T data;

    /**
     * 响应信息提示
     */
    private String message;

    /**
     * 默认创建一个成功的响应
     *
     * @param <R> 响应类型
     * @return 响应成功的Result
     */
    public static <R> RestResult<R> newInstance() {
        return new RestResult<>(true);
    }

    /**
     * 默认创建一个成功的响应,带有响应数据
     *
     * @param r   响应数据
     * @param <R> 响应类型
     * @return 响应数据, 响应成功的Result
     */
    public static <R> RestResult<R> newSuccessInstance(R r) {
        return new RestResult<>(true, r);
    }

    /**
     * 默认创建一个成功的响应,带有响应数据,提示信息
     *
     * @param code 响应数据
     * @param <R>  响应类型
     * @return 响应数据, 响应成功的Result
     */
    public static <R> RestResult<R> newSuccessInstance(int code, String msg) {
        return new RestResult<>(code, true, msg);
    }

    public RestResult<T> withCode(Integer code){
        this.setCode(code);
        return this;
    }

    /**
     * 默认创建一个失败的响应,带有响应数据,提示信息
     *
     * @param r   错误的响应数据
     * @param <R> 响应类型
     * @return 响应数据, 响应失败的Result
     */
    public static <R> RestResult<R> newFailureInstance(R r) {
        return new RestResult<>(false, r);
    }

    /**
     * 默认创建一个失败的响应,带有响应数据,提示信息
     *
     * @param msg 错误的提示信息
     * @param <R> 响应类型
     * @return 响应数据, 响应失败的Result
     */
    public static <R> RestResult<R> newFailureInstance(String msg) {
        return new RestResult<R>(false, msg);
    }

    /**
     * 默认创建一个失败的响应,带有响应数据,提示信息
     *
     * @param msg 错误的提示信息
     * @param <R> 响应类型
     * @return 响应数据, 响应失败的Result
     */
    public static <R> RestResult<R> newFailureInstance(int code, String msg) {
        return new RestResult<>(code, false, msg);
    }

    /**
     * 默认创建一个失败的响应,带有响应数据,提示信息
     *
     * @param status 指定是否成功
     * @param <R>    响应类型
     * @return Result
     */
    public static <R> RestResult<R> newInstance(Boolean status) {
        return new RestResult<>(status);
    }

    private RestResult(Integer code, boolean succeed, String message) {
        this.succeed = succeed;
        this.code = code;
        this.message = message;
    }

    private RestResult(boolean succeed, T data) {
        this.succeed = succeed;
        this.data = data;
    }

    public RestResult() {
    }

    private RestResult(Boolean succeed) {
        this.succeed = succeed;
    }

    private RestResult(Boolean succeed, String message) {
        this.succeed = succeed;
        this.message = message;
    }

    private RestResult(Integer code, Boolean succeed) {
        this.code = code;
        this.succeed = succeed;
    }
}
