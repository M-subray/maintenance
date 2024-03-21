package zerobase.maintenance.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class SignupDto {

  @NotBlank
  @Size(min = 4, max = 10, message = "id는 {min} 자리에서 {max} 자리 사이여야 합니다.")
  private String username;

  @NotBlank
  @Size(min = 6, max = 15, message = "비밀번호는 {min} 자리에서 {max} 자리 사이여야 합니다.")
  private String password;

  @NotBlank
  @Size(min = 2, message = "이름은 두 글자 이상 필수 입력해 주세요.")
  private String name;

  @NotBlank(message = "주소 형식에 맞춰 필수 입력해 주세요.")
  private String address;

  @NotBlank
  @Size(min = 13, max = 13, message = "모바일 번호 형식에 맞춰 필수 입력해 주세요.")
  private String mobile;

  @NotBlank
  @Email(message = "이메일 형식에 맞춰 필수 입력해 주세요.")
  private String mail;

  private String role;

}
