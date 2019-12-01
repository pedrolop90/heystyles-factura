package com.heystyles.factura.api.validator;

import com.heystyles.common.validation.ValidationError;
import com.heystyles.common.validation.Validator;
import com.heystyles.common.validation.groups.Insert;
import com.heystyles.common.validation.groups.Update;
import com.heystyles.factura.api.message.MessageKeys;
import com.heystyles.factura.core.domain.Factura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Component
public class FechaLimiteFacturaValidator implements Validator<Factura> {

    @Autowired
    private MessageSource messageSource;

    @Override
    public List<ValidationError> validate(Factura factura) {
        List<ValidationError> errors = new ArrayList<>();
        if (isFechaMenor(factura.getFechaLimitePago())) {
            errors.add(new ValidationError(
                    "fecha-limite-pago",
                    messageSource.getMessage(MessageKeys.FACTURA_FECHA_MENOR_ACTUAL,
                            null, getLocale())
            ));
        }
        return errors;
    }

    @Override
    public List<Class<?>> getScopes() {
        return Arrays.asList(Insert.class, Update.class);
    }

    private boolean isFechaMenor(LocalDate fecha) {
        return LocalDate.now().compareTo(fecha) > 0;
    }
}
