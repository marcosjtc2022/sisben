package com.bahiana.sisben.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

import com.bahiana.sisben.api.dto.ErroDto;
import com.bahiana.sisben.model.entity.Usuario;

@ControllerAdvice  
@RestController
public class GlobalExceptionHandler extends RuntimeException {
	
	public GlobalExceptionHandler() {
        super();
    }    
	
	private static final long serialVersionUID = 1L;
	  private Integer _line;

	  public GlobalExceptionHandler(final String message, final Integer line) {
	    super(formatMessage(message, line));
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
