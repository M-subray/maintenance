package zerobase.maintenance.dto;

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
public class ReviewDetailCheckDto {
  private Long maintenanceId;
  private String reviewForPartnerInOffice;
  private int starForPartnerInOffice;
  private String reviewForPartnerOnField;
  private int starForPartnerOnField;
}
