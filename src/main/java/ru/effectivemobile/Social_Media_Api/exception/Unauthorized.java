package ru.effectivemobile.Social_Media_Api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.effectivemobile.Social_Media_Api.loger.FormLogInfo;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class Unauthorized extends RuntimeException {
    public Unauthorized() {
        super("Exception: " + FormLogInfo.getInfo());
        System.err.println("Exception: " + FormLogInfo.getException());
    }
}