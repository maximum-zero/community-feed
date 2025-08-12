package org.maximum0.admin.ui.query;

import org.maximum0.admin.ui.dto.GetTableListResponse;
import org.maximum0.admin.ui.dto.users.GetUserTableRequestDto;
import org.maximum0.admin.ui.dto.users.GetUserTableResponseDto;

public interface AdminTableQueryRepository {
    GetTableListResponse<GetUserTableResponseDto> getUserTableData(GetUserTableRequestDto dto);

}
