package zerobase.maintenance.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class SigninDto {
  @NotBlank
  @Size(min = 4, max = 10, message = "id는 {min} 자리에서 {max} 자리 사이여야 합니다.")
  private String username;

  @NotBlank
  @Size(min = 6, max = 15, message = "비밀번호는 {min} 자리에서 {max} 자리 사이여야 합니다.")
  private String password;
}
