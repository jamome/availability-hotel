package com.ruso.apihotel.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ruso.apihotel.util.Constants;

public class ApiLogger {
  private static final Logger log = LoggerFactory.getLogger(ApiLogger.class);

  private ApiLogger() {
    throw new IllegalStateException("Utility class - logger");
  }

  public static void logWhere(String className, String methodName) {
    String msg = Constants.STR_METHOD + Constants.STR_WHITE_SPACE + Constants.STR_ARROW + className
        + Constants.STR_DOT + methodName;
    logDebug(msg);
  }

  public static void logDebug(String msg) {
    log.debug(String.format(Constants.STR_STRING_PARAM, msg));
  }
  
  public static void logError(Exception e) {
    log.error(e.toString());
  }
}
