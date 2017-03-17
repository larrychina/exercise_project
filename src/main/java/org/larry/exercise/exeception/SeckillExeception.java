package org.larry.exercise.exeception;

/**
 * Created by Larry on 2017/2/22.
 */
public class SeckillExeception extends RuntimeException {

    public SeckillExeception(String message) {
        super(message);
    }

    public SeckillExeception(String message, Throwable cause) {
        super(message, cause);
    }
}
