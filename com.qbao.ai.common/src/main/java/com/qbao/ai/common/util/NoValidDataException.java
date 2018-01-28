package com.qbao.ai.common.util;

/**
 * Created by xueming on 2017/3/16.
 */
public class NoValidDataException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = -2749168865492921426L;

    public NoValidDataException(Exception e){
        super(e);
    }

    /**
     */
    public NoValidDataException(String msg) {
        super(msg);
    }

    /**
     * @param message
     * @param e
     */
    public NoValidDataException(String message, Exception e) {
        super(message, e);
    }
}
