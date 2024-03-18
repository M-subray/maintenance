package zerobase.maintenance.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
public class RequestDto {

    @NotBlank(message = "접수 제목을 입력해 주세요.")
    private String title;

    @NotBlank(message = "접수하시려는 물품을 입력해 주세요.")
    private String item;

    @NotNull(message = "구매하신 날짜를 입력해 주세요.")
    private LocalDate purchaseDate;

    @NotBlank(message = "접수내용을 입력해 주세요.")
    private String issueDescription;

}
