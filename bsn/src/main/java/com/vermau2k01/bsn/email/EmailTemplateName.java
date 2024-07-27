package com.vermau2k01.bsn.email;

import lombok.Getter;

@Getter
public enum EmailTemplateName {

    ACTIVATE_ACCOUNT("activation_account");

    EmailTemplateName(String name) {
        this.name = name;
    }

    private final String name;
}
