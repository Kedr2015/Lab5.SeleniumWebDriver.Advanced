package com.epam.exception;

public class UnknownDriverTypeException extends RuntimeException{

  
    
    /**
     * 
     */
    private static final long serialVersionUID = 6066267775593475177L;

    public UnknownDriverTypeException(String msg){
	super(msg);
    }

}
