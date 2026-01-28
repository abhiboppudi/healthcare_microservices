package com.pm.common.config;

import com.pm.common.constant.BaseErrorCode;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ErrorMessageResolver {

  private final MessageSource messageSource;

  public ErrorMessageResolver(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  public String resolve(BaseErrorCode errorCode, Object... args) {
    return messageSource.getMessage(
        errorCode.getCode(),
        args,
        LocaleContextHolder.getLocale()
    );
  }
  
}