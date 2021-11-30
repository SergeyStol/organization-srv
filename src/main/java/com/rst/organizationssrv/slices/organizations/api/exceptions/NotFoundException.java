package com.rst.organizationssrv.slices.organizations.api.exceptions;

import lombok.NonNull;

public class NotFoundException extends RuntimeException {
    public NotFoundException(@NonNull String message) {
        super(message);
    }
}
