package org.seckill.exception;

/**
 * Created by xudong on 2017/3/25.
 */
public class SeckillException extends RuntimeException{

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }

    public SeckillException(String message) {
        super(message);
    }
}
