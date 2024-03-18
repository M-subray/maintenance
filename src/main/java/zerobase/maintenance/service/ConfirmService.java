package zerobase.maintenance.service;


import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zerobase.maintenance.domain.Account;
import zerobase.maintenance.domain.Maintenance;
import zerobase.maintenance.dto.ConfirmDto;
import zerobase.maintenance.dto.SmsForPartnerDto;
import zerobase.maintenance.dto.SmsForUserDto;
import zerobase.maintenance.exception.AccountException;
import zerobase.maintenance.exception.MaintenanceException;
import zerobase.maintenance.repository.AccountRepository;
import zerobase.maintenance.repository.MaintenanceRepository;
import zerobase.maintenance.type.ErrorCode;
import zerobase.maintenance.utils.AuthenticationContext;
import zerobase.maintenance.utils.SmsUtil;

@Service
@RequiredArgsConstructor
public class ConfirmService {

  private final MaintenanceRepository maintenanceRepository;
  private final AccountRepository accountRepository;
  private final VisitCompleteUrlGenerateService visitCompleteURLGenerateService;
  private final SmsUtil smsUtil;


  @Transactional
  public void maintenanceConfirm(Long maintenanceId,
      ConfirmDto confirmDto) {

    Authentication handlerPartnerInOffice =
        AuthenticationContext.getAuthentication();

    Maintenance maintenance =
        maintenanceRepository.findById(maintenanceId).orElseThrow(() ->
            new MaintenanceException(ErrorCode.MAINTENANCE_NOT_FOUND));

    Account handlerPartnerOnField =
        accountRepository.findByUsername(
            confirmDto.getHandlerPartnerOnField()).orElseThrow(()->
            new AccountException(ErrorCode.USERNAME_NOT_FOUND));

    maintenance.confirm(
        handlerPartnerInOffice.getName(),
        handlerPartnerOnField.getUsername(),
        confirmDto.getVisitScheduleDateTime());

    sendTextForPartner(SmsForPartnerDto.builder()
        .userName(maintenance.getAccount().getName())
        .userMobile(maintenance.getAccount().getMobile())
        .address(maintenance.getAccount().getAddress())
        .item(maintenance.getItem())
        .purchaseDate(maintenance.getPurchaseDate())
        .issueDescription(maintenance.getIssueDescription())
        .visitScheduleDateTIme(maintenance.getVisitScheduleDateTime())
        .specialNote(confirmDto.getSpecialNote())
        .mobileForPartner(handlerPartnerOnField.getMobile())
        .build());

    sendTextForUser(SmsForUserDto.builder()
        .partnerName(handlerPartnerOnField.getName())
        .partnerMobile(handlerPartnerOnField.getMobile())
        .visitScheduleDateTIme(maintenance.getVisitScheduleDateTime())
        .userMobile(maintenance.getAccount().getMobile())
        .maintenanceId(maintenanceId)
        .build());
  }

  private void sendTextForPartner(SmsForPartnerDto smsForPartnerDto) {
    DateTimeFormatter visitScheduleFormatter =
        DateTimeFormatter.ofPattern("MM월dd일 HH시mm분");
    String visitScheduleDateTIme =
        smsForPartnerDto.getVisitScheduleDateTIme().format(visitScheduleFormatter);

    DateTimeFormatter purchaseDateFormatter =
        DateTimeFormatter.ofPattern("yyyy년MM월dd일");
    String purchaseDate =
        smsForPartnerDto.getPurchaseDate().format(purchaseDateFormatter);

    String partnerMobile =
        smsForPartnerDto.getMobileForPartner().replaceAll("-", "");

    String message = String.format(
        "유지보수 방문 요청\n"
            + "이름 : %s\n"
            + "핸드폰 : %s\n"
            + "주소 : %s\n"
            + "접수 기기 : %s\n"
            + "구매 일자 : %s\n"
            + "접수 내용 : %s\n"
            + "방문 예정 시간 : %s\n"
            + "특이사항 : %s",
        smsForPartnerDto.getUserName(),
        smsForPartnerDto.getUserMobile(),
        smsForPartnerDto.getAddress(),
        smsForPartnerDto.getItem(),
        purchaseDate,
        smsForPartnerDto.getIssueDescription(),
        visitScheduleDateTIme,
        smsForPartnerDto.getSpecialNote()
    );

    smsUtil.sendOne(partnerMobile, message);
  }

  private void sendTextForUser(SmsForUserDto smsForUserDto) {
    DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern("MM월dd일 HH시mm분");
    String formattedDateTime =
        smsForUserDto.getVisitScheduleDateTIme().format(formatter);
    String userMobile =
        smsForUserDto.getUserMobile().replaceAll("-", "");

    String URL =
        visitCompleteURLGenerateService.generateVisitURL(smsForUserDto.getMaintenanceId());

    String message = String.format(
        "유지보수 방문 안내\n"
            + "방문 기사 이름 : %s\n"
            + "핸드폰 : %s\n"
            + "방문 예정 시간 : %s\n\n"
            + "기사님이 방문 하시면 아래 주소를 눌러 방문을 확정 해주세요.\n\n"
            + URL,
        smsForUserDto.getPartnerName(),
        smsForUserDto.getPartnerMobile(),
        formattedDateTime
    );

    smsUtil.sendOne(userMobile, message);
  }
}
