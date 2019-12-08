package cn.com.attention.utils.utilsresponse;

import java.util.Collection;
import java.util.Map;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/8.
 * @Description:
 */

public interface BaseAssert {
    BaseException newException(Object... var1);

    default void assertNotNull(Object obj) {
        if (obj == null) {
            throw this.newException(obj);
        }
    }

    default void assertNotNull(Object obj, Object... args) {
        if (obj == null) {
            throw this.newException(args);
        }
    }

    default void assertNotTrue(boolean flag) {
        if (!flag) {
            throw this.newException(flag);
        }
    }

    default void assertNotTrue(boolean flag, Object... args) {
        if (!flag) {
            throw this.newException(args);
        }
    }

    default void assertNotEmpty(Map map) {
        if (null == map || map.isEmpty()) {
            throw this.newException(map);
        }
    }

    default void assertNotEmpty(Map map, Object... args) {
        if (null == map || map.isEmpty()) {
            throw this.newException(args);
        }
    }

    default void assertNotEmpty(Collection collection) {
        if (null == collection || collection.isEmpty()) {
            throw this.newException(collection);
        }
    }

    default void assertNotEmpty(Collection collection, Object... args) {
        if (null == collection || collection.isEmpty()) {
            throw this.newException(args);
        }
    }
}
