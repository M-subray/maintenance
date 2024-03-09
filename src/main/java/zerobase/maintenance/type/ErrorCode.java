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

  /*
  Claims Exception
   */
  EXPIRED_TOKEN("토큰이 만료되었습니다."),
  MALFORMED_TOKEN("토큰 형식이 잘못되었습니다."),
  WRONG_SIGNATURE_TOKEN("토큰 서명이 유효하지 않습니다."),
  UNSUPPORTED_TOKEN("지원되지 않는 토큰 형식입니다.")
  ;

  private final String description;
}
