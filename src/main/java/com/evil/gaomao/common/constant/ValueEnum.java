package com.evil.gaomao.common.constant;

import org.springframework.util.Assert;

import java.util.stream.Stream;

/**
 * Enum of value
 * <p>
 * provider a function that according to value search the enum
 *
 * @author fangjiaxiaobai@gmail.com
 * @date 2020-01-14
 * @since 1.0.0
 */
public interface ValueEnum<T> {
    /**
     * get the value
     *
     * @return value
     */
    T getValue();

    /**
     * according to value search the enum
     * @param enumType enum type
     * @param value value
     * @param <V> the value
     * @param <E> the type of enum
     * @return enum
     */
    static <V, E extends ValueEnum<V>> E valueToEnum(Class<E> enumType, V value) {
        Assert.notNull(enumType, "enum type must not be null");
        Assert.notNull(value, "value must not be null");
        Assert.isTrue(enumType.isEnum(), "type must be an enum type");

        return Stream.of(enumType.getEnumConstants())
                .filter(item -> item.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("unknown database value: " + value));
    }

}
