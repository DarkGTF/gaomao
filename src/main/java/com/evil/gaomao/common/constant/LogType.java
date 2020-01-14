package com.evil.gaomao.common.constant;

/**
 * 日志的类别
 *
 * @author fangjiaxiaobai@gmail.com
 * @date 2020-01-14
 * @since 1.0.0
 */
public enum LogType implements ValueEnum<Integer> {
    /**
     * 登录
     */
    LOGGED_IN(25),
    /**
     * 登出
     */
    LOGGED_OUT(30);

    private final Integer value;

    LogType(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
