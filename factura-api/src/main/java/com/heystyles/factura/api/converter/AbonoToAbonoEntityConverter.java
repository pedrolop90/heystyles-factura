package com.heystyles.factura.api.converter;

import com.heystyles.common.exception.APIExceptions;
import com.heystyles.factura.api.dao.AbonoDao;
import com.heystyles.factura.api.dao.FacturaDao;
import com.heystyles.factura.api.entity.AbonoEntity;
import com.heystyles.factura.api.entity.FacturaEntity;
import com.heystyles.factura.api.message.MessageKeys;
import com.heystyles.factura.core.domain.Abono;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Component
public class AbonoToAbonoEntityConverter implements Converter<Abono, AbonoEntity> {

    @Autowired
    private AbonoDao abonoDao;

    @Autowired
    private FacturaDao facturaDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    public AbonoEntity convert(Abono bean) {
        AbonoEntity entity = null;
        if (bean.getId() == null) {
            entity = new AbonoEntity();
        }
        else {
            entity = Optional.ofNullable(abonoDao.findOne(bean.getId()))
                    .orElseThrow(() -> APIExceptions.objetoNoEncontrado(
                            messageSource.getMessage(MessageKeys.ABONO_NOT_FOUND,
                                    new String[]{String.valueOf(bean.getId())},
                                    getLocale())
                    ));
        }

        entity.setId(bean.getId());
        FacturaEntity facturaEntity = Optional.ofNullable(facturaDao.findOne(bean.getFacturaId()))
                .orElseThrow(() -> APIExceptions.objetoNoEncontrado(
                        messageSource.getMessage(MessageKeys.FACTURA_NOT_FOUND,
                                new String[]{String.valueOf(bean.getFacturaId())},
                                getLocale())
                ));
        entity.setFactura(facturaEntity);
        entity.setUsuarioId(bean.getUsuarioId());
        entity.setValor(bean.getValor());
        return entity;
    }
}
