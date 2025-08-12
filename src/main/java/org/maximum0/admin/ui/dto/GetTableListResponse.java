package org.maximum0.admin.ui.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetTableListResponse<T> {
    private List<T> tableData;
    private int totalCount;

}
