package io.daff.springboot.prop;

import org.springframework.beans.factory.Aware;

/**
 * 自定义感知器
 *
 * @author daffupman
 * @since 2020/5/24
 */
public interface MyAware extends Aware {

    void setFlag(Flag flag);
}
