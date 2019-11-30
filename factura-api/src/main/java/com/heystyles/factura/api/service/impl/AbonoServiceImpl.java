package com.heystyles.factura.api.service.impl;

import com.heystyles.common.exception.APIExceptions;
import com.heystyles.common.service.impl.ServiceImpl;
import com.heystyles.common.types.Page;
import com.heystyles.factura.api.dao.AbonoDao;
import com.heystyles.factura.api.entity.AbonoEntity;
import com.heystyles.factura.api.message.MessageKeys;
import com.heystyles.factura.api.service.AbonoService;
import com.heystyles.factura.core.domain.Abono;
import com.heystyles.factura.core.dto.AbonoListResponse;
import com.heystyles.factura.core.filter.AbonoFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Service
public class AbonoServiceImpl
        extends ServiceImpl<Abono, AbonoEntity, Long> implements AbonoService {

    @Autowired
    private AbonoDao abonoDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    protected CrudRepository<AbonoEntity, Long> getDao() {
        return null;
    }

    @Override
    public Abono getAbono(Long abonoId) {
        return Optional.ofNullable(findById(abonoId))
                .orElseThrow(() -> APIExceptions.objetoNoEncontrado(
                        messageSource.getMessage(MessageKeys.ABONO_NOT_FOUND,
                                new String[]{String.valueOf(abonoId)},
                                getLocale())
                ));
    }

    @Override
    public AbonoListResponse getFilter(AbonoFilter filter) {
        filter = Optional.ofNullable(filter).orElse(new AbonoFilter());
        Page<AbonoEntity> page = abonoDao.getPage(filter);
        return new AbonoListResponse(
                page.getTotalElements(),
                getConverterService().convertTo(page.getContent(), Abono.class)
        );
    }
}
