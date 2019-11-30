package com.heystyles.factura.api.exception;

import com.heystyles.common.exception.ServerJpaResponseExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class FacturaServerException extends ServerJpaResponseExceptionHandler {
}
