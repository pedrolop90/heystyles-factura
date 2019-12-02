package com.heystyles.factura.api.converter;

import com.heystyles.common.exception.APIExceptions;
import com.heystyles.factura.api.dao.ParametroGeneralDao;
import com.heystyles.factura.api.entity.ParametroGeneralEntity;
import com.heystyles.factura.api.message.MessageKeys;
import com.heystyles.factura.core.domain.ParametroGeneral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Component
public class ParametroGeneralToParametroGeneralEntityConverter
        implements Converter<ParametroGeneral, ParametroGeneralEntity> {

    @Autowired
    private ParametroGeneralDao parametroGeneralDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    public ParametroGeneralEntity convert(ParametroGeneral bean) {
        ParametroGeneralEntity entity = null;
        if (bean.getId() == null) {
            entity = new ParametroGeneralEntity();
        }
        else {
            entity = Optional.ofNullable(parametroGeneralDao.findOne(bean.getId()))
                    .orElseThrow(() -> APIExceptions.objetoNoEncontrado(
                            messageSource.getMessage(MessageKeys.PARAMETRO_GENERAL_NOT_FOUND,
                                    new String[]{String.valueOf(bean.getId())},
                                    getLocale())
                    ));
        }
        entity.setNombre(bean.getNombre());
        entity.setValor(bean.getValor());
        return entity;
    }
}
