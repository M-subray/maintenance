package zerobase.maintenance.dto;

import java.time.LocalDate;
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
public class SmsForPartnerDto {
  private String userName;
  private String userMobile;
  private String address;
  private String item;
  private LocalDate purchaseDate;
  private String issueDescription;
  private LocalDateTime visitScheduleDateTIme;
  private String specialNote;

  private String mobileForPartner;
}
