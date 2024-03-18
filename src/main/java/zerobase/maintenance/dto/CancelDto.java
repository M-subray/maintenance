package zerobase.maintenance.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CancelDto {
    @NotBlank(message = "취소 이유를 입력해 주세요.")
    private String cancelReason;
}
