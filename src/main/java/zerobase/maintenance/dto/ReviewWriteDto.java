package zerobase.maintenance.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zerobase.maintenance.type.Star;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewWriteDto {
  @NotNull(message = "접수 응대 사원에 대한 별점을 남겨주세요.")
  private Star starForPartnerInOffice;
  private String reviewForPartnerInOffice;

  @NotNull(message = "방문 처리 사원에 대한 별점을 남겨주세요.")
  private Star starForPartnerOnField;
  private String reviewForPartnerOnField;
}
