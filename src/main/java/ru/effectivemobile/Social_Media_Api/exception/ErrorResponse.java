package ru.effectivemobile.Social_Media_Api.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * эксепш - класс обертка
 */

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private String exMessage;
}
