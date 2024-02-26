package zerobase.maintenance.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Entity
@Table(name = "maintenance")
public class Maintenance {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "account_id")
  private Long accountId;

  @Column(name = "item")
  private String item;

  @Column(name = "purchase_date")
  private LocalDate purchaseDate;

  @Column(name = "issue_description")
  private String issueDescription;

  @Column(name = "handler_partner_in_office")
  private String handlerPartnerInOffice;

  @Column(name = "handler_partner_on_field")
  private String handlerPartnerOnField;

  @Column(name = "request_date")
  private LocalDateTime requestDate;

  @Column(name = "visit_schedule")
  private LocalDateTime visitSchedule;

  @Column(name = "visit_completion")
  private LocalDateTime visitCompletion;

  @Column(name = "request_status")
  private String requestStatus;
}
