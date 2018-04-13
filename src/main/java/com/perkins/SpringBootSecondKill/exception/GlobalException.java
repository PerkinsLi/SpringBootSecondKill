package com.perkins.SpringBootSecondKill.exception;

import com.perkins.SpringBootSecondKill.result.CodeMsg;

public class GlobalException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private CodeMsg codeMsg;
	
	public GlobalException(CodeMsg codeMsg) {
		this.codeMsg = codeMsg;
	}

	public CodeMsg getCodeMsg() {
		return codeMsg;
	}
	
	

}
