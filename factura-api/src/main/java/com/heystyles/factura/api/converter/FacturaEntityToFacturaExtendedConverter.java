package com.heystyles.factura.api.converter;

import com.heystyles.common.service.ConverterService;
import com.heystyles.factura.api.entity.FacturaEntity;
import com.heystyles.factura.core.domain.Factura;
import com.heystyles.factura.core.domain.FacturaExtended;
import com.heystyles.usuarios.cliente.ProveedorClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FacturaEntityToFacturaExtendedConverter implements Converter<FacturaEntity, FacturaExtended> {

    @Autowired
    private ConverterService converterService;

    @Autowired
    private ProveedorClient proveedorClient;

    @Override
    public FacturaExtended convert(FacturaEntity entity) {
        FacturaExtended bean = new FacturaExtended();
        bean.setFactura(converterService.convertTo(entity, Factura.class));
        bean.setProveedor(proveedorClient.findProveedorById(entity.getProveedorId()));
        return bean;
    }
}
