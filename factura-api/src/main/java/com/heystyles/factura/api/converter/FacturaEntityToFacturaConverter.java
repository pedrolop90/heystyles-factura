package com.heystyles.factura.api.converter;

import com.heystyles.factura.api.entity.FacturaEntity;
import com.heystyles.factura.core.domain.Factura;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FacturaEntityToFacturaConverter implements Converter<FacturaEntity, Factura> {

    @Override
    public Factura convert(FacturaEntity entity) {
        Factura bean = new Factura();
        bean.setId(entity.getId());
        bean.setProveedorId(entity.getProveedorId());
        bean.setValorTotal(entity.getValorTotal());
        bean.setPorcentajeIva(entity.getPorcentajeIva());
        bean.setPorcentajeDescuento(entity.getPorcentajeDescuento());
        bean.setFechaLimitePago(entity.getFechaLimitePago());
        bean.setfPago(entity.isfPago());
        return bean;
    }
}
