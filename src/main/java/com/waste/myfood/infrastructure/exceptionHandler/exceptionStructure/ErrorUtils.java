package com.waste.myfood.infrastructure.exceptionHandler.exceptionStructure;

public class ErrorUtils {
    ErrorUtils() {

    }
  
    public static Error createError(final String errorCode, final String messageKey, final Integer httpCode) {
      final Error error = new Error();
      error.setErrorCode(errorCode);
      error.setMessage(messageKey);
      error.setHttpCode(httpCode);
      return error;
    }
}
