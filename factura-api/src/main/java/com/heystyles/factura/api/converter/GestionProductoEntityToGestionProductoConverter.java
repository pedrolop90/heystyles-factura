package com.heystyles.factura.api.converter;

import com.heystyles.factura.api.entity.GestionProductoEntity;
import com.heystyles.factura.core.domain.GestionProducto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GestionProductoEntityToGestionProductoConverter
        implements Converter<GestionProductoEntity, GestionProducto> {

    @Override
    public GestionProducto convert(GestionProductoEntity entity) {
        GestionProducto bean = new GestionProducto();
        bean.setId(entity.getId());
        bean.setCantidad(entity.getCantidad());
        bean.setEstadoEntrada(entity.getEstadoEntrada());
        bean.setMarcaProductoId(entity.getMarcaProductoId());
        bean.setPorcentajeDescuento(entity.getPorcentaDescuento());
        bean.setValor(entity.getValor());
        return bean;
    }
}
