package com.heystyles.factura.api.converter;

import com.heystyles.common.service.ConverterService;
import com.heystyles.factura.api.entity.GestionProductoEntity;
import com.heystyles.factura.api.service.GestionProductoService;
import com.heystyles.factura.core.domain.GestionProducto;
import com.heystyles.factura.core.domain.GestionProductoExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GestionProductoEntityToGestionProductoExtendedConverter
        implements Converter<GestionProductoEntity, GestionProductoExtended> {

    @Autowired
    private GestionProductoService gestionProductoService;

    @Autowired
    private ConverterService converterService;

    @Override
    public GestionProductoExtended convert(GestionProductoEntity entity) {
        GestionProductoExtended bean = new GestionProductoExtended();
        bean.setGestionProducto(converterService.convertTo(entity, GestionProducto.class));
        bean.setMarcasProductos(gestionProductoService.findMarcaProductoByFacturaId(entity.getFactura().getId()));
        return bean;
    }
}
