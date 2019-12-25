package com.evil.gaomao.common.constant;

/**
 * 响应码常亮
 *
 * @author fangjiaxioabai@gmail.com
 * @date 2019-12-25
 * @since 1.0.0
 */
public interface ResponseCode {

    /**
     * 服务器成功返回用户请求的数据
     */
    int OK = 200;
    /**
     * 新建或修改数据成功
     */
    int CREATED = 201;
    /**
     * 删除数据成功
     */
    int NO_CONTENT = 204;
    /**
     * 用户发出的请求有问题
     */
    int BAD_REQUEST = 400;
    /**
     * 用户未认证，无法进行此操作
     */
    int UNAUTHORIZED = 401;
    /**
     * 用户无权限进行此操作
     */
    int FORBIDDEN = 403;
    /**
     * 找不到资源
     */
    int NOT_FOUND = 404;

    /**
     * 创建一个对象时，发生验证错误
     */
    int UNPROCESSABLE_ENTITY = 422;
    /**
     * 服务器端错误
     */
    int INTERNAL_SERVER = 500;

    /**
     * 无法严明的错误
     */
    int OTHER = 0;


}
