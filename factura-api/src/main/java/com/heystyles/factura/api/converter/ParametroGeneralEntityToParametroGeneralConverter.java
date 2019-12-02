package com.heystyles.factura.api.converter;

import com.heystyles.factura.api.entity.ParametroGeneralEntity;
import com.heystyles.factura.core.domain.ParametroGeneral;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ParametroGeneralEntityToParametroGeneralConverter
        implements Converter<ParametroGeneralEntity, ParametroGeneral> {

    @Override
    public ParametroGeneral convert(ParametroGeneralEntity entity) {
        ParametroGeneral bean = new ParametroGeneral();
        bean.setId(entity.getId());
        bean.setNombre(entity.getNombre());
        bean.setValor(entity.getValor());
        return bean;
    }
}
