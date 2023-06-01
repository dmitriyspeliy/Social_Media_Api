package ru.effectivemobile.Social_Media_Api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.effectivemobile.Social_Media_Api.loger.FormLogInfo;


/**
 * эксепш - класс
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ElemNotFoundChecked extends RuntimeException {
    public ElemNotFoundChecked() {
        super("Exception: " + FormLogInfo.getInfo());
        System.err.println("Exception: " + FormLogInfo.getException());
    }

    public ElemNotFoundChecked(String textMessage) {
        super("Exception: " + textMessage + FormLogInfo.getInfo());
        System.err.println("Exception: " + textMessage + FormLogInfo.getException());
    }
}

