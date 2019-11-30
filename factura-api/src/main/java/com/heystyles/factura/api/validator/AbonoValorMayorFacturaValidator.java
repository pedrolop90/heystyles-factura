package com.heystyles.factura.api.validator;

import com.heystyles.common.validation.ValidationError;
import com.heystyles.common.validation.Validator;
import com.heystyles.common.validation.groups.Insert;
import com.heystyles.common.validation.groups.Update;
import com.heystyles.factura.api.message.MessageKeys;
import com.heystyles.factura.api.service.FacturaService;
import com.heystyles.factura.core.domain.Abono;
import com.heystyles.factura.core.domain.Factura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Component
public class AbonoValorMayorFacturaValidator implements Validator<Abono> {

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private MessageSource messageSource;

    @Override
    public List<ValidationError> validate(Abono abono) {
        List<ValidationError> errors = new ArrayList<>();
        Factura factura = facturaService.getFactura(abono.getFacturaId());
        if (abono.getValor() > factura.getValorTotal()) {
            errors.add(new ValidationError(
                    "valor",
                    messageSource.getMessage(MessageKeys.ABONO_MAYOR_FACTURA_VALOR,
                            null, getLocale())
            ));
        }
        return errors;
    }

    @Override
    public List<Class<?>> getScopes() {
        return Arrays.asList(Insert.class, Update.class);
    }
}
