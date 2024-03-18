package zerobase.maintenance.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import zerobase.maintenance.type.Status;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Maintenance {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "account_id")
  private Account account;

  private String title;
  private String item;
  private LocalDate purchaseDate;
  private String issueDescription;
  private String handlerPartnerInOffice;
  private String handlerPartnerOnField;

  @CreatedDate
  private LocalDateTime requestDateTime;

  private LocalDateTime visitScheduleDateTime;
  private LocalDateTime visitCompletionDateTime;

  @Enumerated(EnumType.STRING)
  private Status requestStatus;

  public void confirm(String handlerPartnerInOffice
      , String handlerPartnerOnField
      , LocalDateTime visitScheduleDateTime) {
    this.handlerPartnerInOffice = handlerPartnerInOffice;
    this.handlerPartnerOnField = handlerPartnerOnField;
    this.visitScheduleDateTime = visitScheduleDateTime;
    this.requestStatus = Status.CONFIRMED;
  }
}
