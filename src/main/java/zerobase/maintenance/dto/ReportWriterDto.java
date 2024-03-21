package zerobase.maintenance.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportWriterDto {
  @NotBlank(message = "제목을 입력해 주세요.")
  private String title;

  private MultipartFile image;

  @NotBlank(message = "내용을 입력해 주세요.")
  private String reportDetail;
}
