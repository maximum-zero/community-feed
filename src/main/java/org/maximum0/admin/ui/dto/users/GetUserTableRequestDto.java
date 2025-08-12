package org.maximum0.admin.ui.dto.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.maximum0.common.domain.Pageable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetUserTableRequestDto extends Pageable {
    private String name;

}
