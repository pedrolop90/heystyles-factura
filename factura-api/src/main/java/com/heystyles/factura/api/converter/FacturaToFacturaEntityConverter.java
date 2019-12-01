package com.heystyles.factura.api.converter;

import com.heystyles.common.exception.APIExceptions;
import com.heystyles.factura.api.dao.FacturaDao;
import com.heystyles.factura.api.entity.FacturaEntity;
import com.heystyles.factura.api.message.MessageKeys;
import com.heystyles.factura.core.domain.Factura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Component
public class FacturaToFacturaEntityConverter implements Converter<Factura, FacturaEntity> {

    @Autowired
    private FacturaDao facturaDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    public FacturaEntity convert(Factura bean) {
        FacturaEntity entity = null;
        if (bean.getId() == null) {
            entity = new FacturaEntity();
        }
        else {
            entity = Optional.ofNullable(facturaDao.findOne(bean.getId()))
                    .orElseThrow(() -> APIExceptions.objetoNoEncontrado(
                            messageSource.getMessage(MessageKeys.FACTURA_NOT_FOUND,
                                    new String[]{String.valueOf(bean.getId())},
                                    getLocale()
                            )));
        }
        entity.setProveedorId(bean.getProveedorId());
        entity.setValorTotal(bean.getValorTotal());
        entity.setPorcentajeIva(bean.getPorcentajeIva());
        entity.setPorcentajeDescuento(bean.getPorcentajeDescuento());
        entity.setFechaLimitePago(bean.getFechaLimitePago());
        entity.setfPago(bean.isfPago());
        return entity;
    }
}
