package zerobase.maintenance.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfirmDto {

  @NotBlank(message = "방문하게 될 인원을 입력해 주세요")
  private String handlerPartnerOnField;

  @NotNull(message = "방문 예정 시간을 입력해 주세요.")
  private LocalDateTime visitScheduleDateTime;

  private String specialNote;
}
