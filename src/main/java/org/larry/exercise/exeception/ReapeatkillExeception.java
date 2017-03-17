package org.larry.exercise.exeception;

/**
 * Created by Larry on 2017/2/22.
 */
public class ReapeatkillExeception extends RuntimeException {
    public ReapeatkillExeception(String message) {
        super(message);
    }

    public ReapeatkillExeception(String message, Throwable cause) {
        super(message, cause);
    }
}
