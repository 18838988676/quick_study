package com.cn.contrurent.four;


/**
 * Counter
 * <p/>
 * Simple thread-safe counter using the Java monitor pattern
 *
 * @author Brian Goetz and Tim Peierls
 */
public final class Counter2 {
    private long value = 0;

    public  long getValue() {
        return value;
    }

    public  long increment() {
        if (value == Long.MAX_VALUE){
            throw new IllegalStateException("counter overflow");}
        return ++value;
    }
}
