package org.larry.exercise.exeception;

/**
 * Created by Larry on 2017/2/22.
 * 秒少关闭异常
 */
public class SeckillCloseExeception extends RuntimeException {
    public SeckillCloseExeception(String message) {
        super(message);
    }

    public SeckillCloseExeception(String message, Throwable cause) {
        super(message, cause);
    }
}
