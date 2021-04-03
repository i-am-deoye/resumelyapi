package com.resumly.resumeapi.core.domain;

public enum ResponseCode {
    DEFAULT_SERVER_ERROR("99", "Server Error"),
    SUCCESS("00", "SUCCESS"),
    SUCCESSFUL("00", "Successful"),
    USER_NOT_FOUND_ERROR("98", "User Not Found Error"),
    INVALID_CHALLENGE_ANSWER_ERROR("97", "Invalid Challenge Answer Error"),
    USER_DEACTIVATED("101", "User auth has been deactivated"),
    INVALID_USER("102", "Invalid user"),
    OTP_SEND_ERROR("103", "Error sending OTP"),
    FAILED_OPERATION_ERROR("104", "Operation Failed"),
    PAYMENT_GATEWAY_NOT_AVAILABLE("301", "Payment Gateway not available"),
    SERVICE_NOT_AVAILABLE("302", "Service not available"),
    INVALID_CUSTOMER_ID("100", "The provided Customer ID is not valid"),
    ITEM_NOT_FOUND("104", "Item not Found"),
    USER_ALREADY_EXISTS("105", "USER_ALREADY_EXISTS"),
    INVALID_USER_CREDENTIAL("106", "INVALID_USER_CREDENTIAL"),
    MERCHANT_ALREADY_EXISTS("107", "MERCHANT_ALREADY_EXISTS"),
    DAN_RULE_NOT_FOUND("108", "DAN_RULE_NOT_FOUND"),
    USEROPERAND_NOT_MATCH("109", "USEROPERAND_NOT_MATCH");

    private String code;
    private String description;

    private ResponseCode(String code, String description) {
        this.setCode(code);
        this.setDescription(description);
    }

    public String getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}