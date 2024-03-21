package zerobase.maintenance.dto;

import java.time.LocalDateTime;
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
public class ReportDetailCheckDto {
  private String title;
  private String item;
  private String handlerPartnerInOffice;
  private String handlerPartnerOnField;
  private String visitCompletionDateTIme;
  private String imagePath;
  private String reportDetail;
}
