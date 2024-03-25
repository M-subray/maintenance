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
public class ReviewCheckDto {
  private Long maintenanceId;
  private String maintenanceTitle;
  private String handlerPartner;
}
