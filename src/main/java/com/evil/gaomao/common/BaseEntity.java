package com.evil.gaomao.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.Transient;
import java.time.LocalDateTime;

/**
 * 公共基础实体类
 *
 * @author fangajiaxiaobai@gmail.com
 * @date 2019-12-25
 * @since 1.0.0
 */
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    // 创建时间的开始时间(用于按照时间间隔进行范围查询)
    @Transient
    protected LocalDateTime createDateStart;

    // 创建时时的结束时间(用于按照时间间隔进行范围查询)
    @Transient
    protected LocalDateTime createDateEnd;

    // 创建时间的开始时间(用于按照时间间隔进行范围查询)
    @Transient
    protected LocalDateTime updateDateStart;

    // 创建时时的结束时间(用于按照时间间隔进行范围查询)
    @Transient
    protected LocalDateTime updateDateEnd;

    // 分页大小(用于请求时使用)
    @Transient
    private int pageSize = 10;

    // 当前请求页(用于请求时使用)
    @Transient
    private int pageNum = 0;

    @Transient
    private String ids;

    public abstract Integer getId();

}
