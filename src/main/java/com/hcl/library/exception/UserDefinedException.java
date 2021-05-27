package com.hcl.library.exception;

public class UserDefinedException extends Exception{
	private static final long serialVersionUID = 1L;
	private String msg;
	 public UserDefinedException(String msg) {
		 this.msg=msg;
	 }
	 @Override
	 public String getMessage() {
		return this.msg;
		 
	 }
}

