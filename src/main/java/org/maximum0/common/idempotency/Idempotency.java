package org.maximum0.common.idempotency;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.maximum0.common.ui.Response;

@Getter
@AllArgsConstructor
public class Idempotency {
    private final String key;
    private final Response<?> response;
}
