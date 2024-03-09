package zerobase.maintenance.dto;

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
public class ErrorResponse {
  private ErrorCode errorCode;
  private String errorMessage;
}
