package com.graduation.votingSystem.util.exception;

import lombok.Getter;

@Getter
public class ErrorInfo {
    private final String url;
    private final String detail;

    public ErrorInfo(String url, String detail) {
        this.url = url;
        this.detail = detail;
    }
}
