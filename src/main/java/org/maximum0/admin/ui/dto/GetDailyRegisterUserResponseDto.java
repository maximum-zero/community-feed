package org.maximum0.admin.ui.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetDailyRegisterUserResponseDto {
    private LocalDate date;
    private Long count;
}
