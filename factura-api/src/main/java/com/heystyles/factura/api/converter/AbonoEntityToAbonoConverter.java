package com.heystyles.factura.api.converter;

import com.heystyles.factura.api.entity.AbonoEntity;
import com.heystyles.factura.core.domain.Abono;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AbonoEntityToAbonoConverter implements Converter<AbonoEntity, Abono> {

    @Override
    public Abono convert(AbonoEntity entity) {
        Abono bean = new Abono();
        bean.setId(entity.getId());
        bean.setFacturaId(entity.getFactura().getId());
        bean.setUsuarioId(entity.getUsuarioId());
        bean.setValor(entity.getValor());
        return bean;
    }
}
