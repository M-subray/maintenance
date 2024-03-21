package zerobase.maintenance.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
  /*
  Account Exception
   */
  USERNAME_NOT_FOUND("해당 username 은 존재하지 않습니다."),
  ALREADY_USERNAME("해당 username 은 이미 존재 합니다."),
  WRONG_PASSWORD("비밀번호가 다릅니다."),
  SIGNIN_TIME_OUT("재로그인이 필요합니다."),

  /*
  Claims Exception
   */
  EXPIRED_TOKEN("토큰이 만료되었습니다."),
  MALFORMED_TOKEN("토큰 형식이 잘못되었습니다."),
  WRONG_SIGNATURE_TOKEN("토큰 서명이 유효하지 않습니다."),
  UNSUPPORTED_TOKEN("지원되지 않는 토큰 형식입니다."),

  /*
  Maintenance Exception
   */
  CAN_NOT_CANCEL("취소가 불가하거나 이미 취소된 상태입니다. (유선 문의 부탁드립니다.)"),
  CAN_NOT_COMPLETE("완료가 불가하거나 이미 완료된 상태입니다. (컨펌 우선 진행 필요)"),
  REQUEST_NOT_ALLOWED("요청 권한이 없습니다."),
  MAINTENANCE_NOT_FOUND("해당 접수건이 존재하지 않습니다."),

  /*
  Url Exception
   */
  NOT_GENERATE_URL("URL이 생성 되지 못했습니다. 접수처로 문의 바랍니다."),
  URL_EXPIRED("URL의 유효시간이 만료 되었습니다."),

  /*
  Report Exception
   */
  NOT_FOUND_REPORT("해당 리포트는 존재하지 않습니다."),


  /*
  FileCopy Exception
   */
  FILE_COPY_FAILED("파일 저장을 실패했습니다."),
  FILE_MAX_SIZE_EXCEED("파일 크기가 기준치(10MB) 보다 큽니다.")
  ;

  private final String description;
}
