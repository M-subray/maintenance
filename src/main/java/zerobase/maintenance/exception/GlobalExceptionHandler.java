package zerobase.maintenance.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(AccountException.class)
  public ResponseEntity<String> handleAccountException(AccountException e) {
    log.error("에러코드: {}, 에러 메시지: {}", e.getErrorCode(), e.getErrorMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getErrorMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException e) {
    String errorMessage = e.getBindingResult().getFieldError().getDefaultMessage();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
  }
}

