package com.heystyles.factura.api.converter;

import com.heystyles.common.exception.APIExceptions;
import com.heystyles.factura.api.dao.GestionProductoDao;
import com.heystyles.factura.api.entity.GestionProductoEntity;
import com.heystyles.factura.api.message.MessageKeys;
import com.heystyles.factura.core.domain.GestionProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Component
public class GestionProductoToGestionProductoEntityConverter
        implements Converter<GestionProducto, GestionProductoEntity> {

    @Autowired
    private GestionProductoDao gestionProductoDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    public GestionProductoEntity convert(GestionProducto bean) {
        GestionProductoEntity entity = null;
        if (bean.getId() == null) {
            entity = new GestionProductoEntity();
        }
        else {
            entity = Optional.ofNullable(gestionProductoDao.findOne(bean.getId()))
                    .orElseThrow(() -> APIExceptions.objetoNoEncontrado(
                            messageSource.getMessage(
                                    MessageKeys.GESTION_PRODUCTO_NOT_FOUND,
                                    new String[]{String.valueOf(bean.getId())},
                                    getLocale()
                            )
                    ));
        }
        entity.setMarcaProductoId(bean.getMarcaProductoId());
        entity.setValor(bean.getValor());
        entity.setCantidad(bean.getCantidad());
        entity.setEstadoEntrada(bean.getEstadoEntrada());
        entity.setPorcentaDescuento(bean.getPorcentajeDescuento());
        return entity;
    }
}
