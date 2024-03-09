package zerobase.maintenance.exception;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import zerobase.maintenance.type.ErrorCode;

@Getter
@Setter
@AllArgsConstructor
@NotNull
@Builder
public class ClaimsException extends RuntimeException{
  private ErrorCode errorCode;
  private String errorMessage;

  public ClaimsException(ErrorCode errorCode) {
    this.errorCode = errorCode;
    this.errorMessage = errorCode.getDescription();
  }
}
