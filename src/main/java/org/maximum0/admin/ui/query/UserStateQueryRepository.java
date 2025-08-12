package org.maximum0.admin.ui.query;

import java.util.List;
import org.maximum0.admin.ui.dto.users.GetDailyRegisterUserResponseDto;

public interface UserStateQueryRepository {
    List<GetDailyRegisterUserResponseDto> getDailyRegisterUserState(int beforeDays);

}
