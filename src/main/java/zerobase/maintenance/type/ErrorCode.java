package zerobase.maintenance.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
  USERNAME_NOT_FOUND("해당 username 은 존재하지 않습니다."),
  ALREADY_USERNAME("해당 username 은 이미 존재 합니다."),
  WRONG_PASSWORD("비밀번호가 다릅니다.")
  ;

  private final String description;
}
