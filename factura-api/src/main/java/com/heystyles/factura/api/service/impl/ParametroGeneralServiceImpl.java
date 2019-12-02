package com.heystyles.factura.api.service.impl;

import com.heystyles.common.exception.APIExceptions;
import com.heystyles.common.service.impl.ServiceImpl;
import com.heystyles.factura.api.dao.ParametroGeneralDao;
import com.heystyles.factura.api.entity.ParametroGeneralEntity;
import com.heystyles.factura.api.message.MessageKeys;
import com.heystyles.factura.api.service.ParametroGeneralService;
import com.heystyles.factura.core.domain.ParametroGeneral;
import com.heystyles.factura.core.domain.ParametroGeneralEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Service
public class ParametroGeneralServiceImpl
        extends ServiceImpl<ParametroGeneral, ParametroGeneralEntity, Long> implements ParametroGeneralService {

    @Autowired
    private ParametroGeneralDao parametroGeneralDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    protected CrudRepository<ParametroGeneralEntity, Long> getDao() {
        return parametroGeneralDao;
    }

    @Override
    public ParametroGeneral getParametroGeneral(Long parametroGeneralId) {
        return Optional.ofNullable(findById(parametroGeneralId))
                .orElseThrow(() -> APIExceptions.objetoNoEncontrado(
                        messageSource.getMessage(MessageKeys.PARAMETRO_GENERAL_NOT_FOUND,
                                new String[]{String.valueOf(parametroGeneralId)},
                                getLocale())
                ));
    }

    @Override
    public ParametroGeneral getParametroGeneralByEnum(ParametroGeneralEnum parametroGeneralEnum) {
        return Optional.ofNullable(findById(parametroGeneralEnum.getId()))
                .orElseThrow(() -> APIExceptions.objetoNoEncontrado(
                        messageSource.getMessage(MessageKeys.PARAMETRO_GENERAL_NOT_FOUND,
                                new String[]{String.valueOf(parametroGeneralEnum.getId())},
                                getLocale())
                ));
    }
}
