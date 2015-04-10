package com.rave.visiit.util;

public class VisiitRunTimeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2316658267978703058L;
	
	
	public VisiitRunTimeException() {
        super();
    }
	
    public VisiitRunTimeException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public VisiitRunTimeException(String message) {
        super(message);
    }
    
    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
