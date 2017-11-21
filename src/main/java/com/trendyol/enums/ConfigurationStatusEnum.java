package com.trendyol.enums;

public enum ConfigurationStatusEnum {

    NOT_ACTIVE(0), ACTIVE(1);

    private int status;

    ConfigurationStatusEnum(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
