package com.naranja.transactions.dto;

import java.util.List;

public class ErrorResponse
{
    private Integer code;
    private String message;
    private List<String> violations;

    public ErrorResponse(Integer code, String message, List<String> violations) {
        this.message = message;
        this.violations = violations;
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getViolations() {
        return violations;
    }

    public void setViolations(List<String> violations) {
        this.violations = violations;
    }
}