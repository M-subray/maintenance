package zerobase.maintenance.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
@Entity
public class Maintenance {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "account_id")
  private Account account;
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
