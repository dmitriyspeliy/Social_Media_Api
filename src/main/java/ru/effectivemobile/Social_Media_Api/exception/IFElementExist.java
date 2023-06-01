package ru.effectivemobile.Social_Media_Api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.effectivemobile.Social_Media_Api.loger.FormLogInfo;


/**
 * эксепш - класс
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IFElementExist extends RuntimeException {
    public IFElementExist(String textMessage) {
        super("Exception: " + textMessage + FormLogInfo.getInfo());
        System.err.println("Exception: " + textMessage + FormLogInfo.getException());
    }
}