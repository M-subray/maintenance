package zerobase.maintenance.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zerobase.maintenance.type.Status;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestCheckForUserDto {
    private String title;
    private LocalDateTime requestDateTime;
    private Status requestStatus;
}
