package com.bahiana.sisben.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice  
@RestController
@ResponseBody
public class GlobalExceptionHandler extends RuntimeException {
	
	public GlobalExceptionHandler() {
        super();
    }    
	
	private static final long serialVersionUID = 1L;
	  private Integer _line;

	  public GlobalExceptionHandler(final String message, final Integer line) {
	    super(formatMessage(message, line));
	  }
	  
	  public GlobalExceptionHandler(String message) {
		  super(message);
	 }

	  public static String formatMessage(String message, Integer line) {
	    return new StringBuilder()
	               .append("{message: ")
	               .append("'")
	               .append(message)
	               .append("'")
	               .append(", linha: ")
	               .append("'")
	               .append(line)
	               .append("'")
	               .append("}").toString();
	  }
	

	
	
	
	
	
}
