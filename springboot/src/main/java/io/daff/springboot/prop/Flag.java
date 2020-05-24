package io.daff.springboot.prop;

import org.springframework.stereotype.Component;

/**
 * @author daffupman
 * @since 2020/5/24
 */
@Component
public class Flag {

    private boolean canOperate = true;

    public boolean isCanOperate() {
        return canOperate;
    }

    public void setCanOperate(boolean canOperate) {
        this.canOperate = canOperate;
    }
}
