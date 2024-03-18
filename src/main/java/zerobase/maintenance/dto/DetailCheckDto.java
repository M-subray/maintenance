package zerobase.maintenance.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zerobase.maintenance.type.Status;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailCheckDto {
  private String name;
  private String address;
  private String mobile;
  private String title;
  private String item;
  private LocalDate purchaseDate;
  private String issueDescription;
  private String handlerPartnerInOffice;
  private String handlerPartnerOnField;
  private LocalDateTime requestDateTime;
  private LocalDateTime visitScheduleDateTime;
  private LocalDateTime visitCompletionDateTime;
  private Status requestStatus;
}
