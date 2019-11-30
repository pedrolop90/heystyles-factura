package com.heystyles.factura.api.service.impl;

import com.heystyles.common.exception.APIExceptions;
import com.heystyles.common.service.impl.ServiceImpl;
import com.heystyles.factura.api.dao.FacturaDao;
import com.heystyles.factura.api.dao.GestionProductoDao;
import com.heystyles.factura.api.entity.FacturaEntity;
import com.heystyles.factura.api.message.MessageKeys;
import com.heystyles.factura.api.service.FacturaService;
import com.heystyles.factura.api.service.GestionProductoService;
import com.heystyles.factura.core.domain.Factura;
import com.heystyles.factura.core.domain.FacturaExtended;
import com.heystyles.factura.core.dto.FacturaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Service
public class FacturaServiceImpl
        extends ServiceImpl<Factura, FacturaEntity, Long> implements FacturaService {

    @Autowired
    private FacturaDao facturaDao;

    @Autowired
    private GestionProductoService gestionProductoService;

    @Autowired
    private GestionProductoDao gestionProductoDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    protected CrudRepository<FacturaEntity, Long> getDao() {
        return facturaDao;
    }

    @Override
    public Long insert(FacturaRequest request) {
        Factura factura = request.getFactura();
        Long id = super.insert(factura);
        gestionProductoService.upsert(id, request.getGestionProductos());
        Double valorTotal = gestionProductoDao.valorTotalGestionProductoByFacturaId(id);
        factura.setId(id);
        factura.setValorTotal(valorTotal);
        super.update(factura);
        return id;
    }

    @Override
    public void update(FacturaRequest request) {
        super.update(request.getFactura());
        gestionProductoService.upsert(request.getFactura().getId(), request.getGestionProductos());
    }

    @Override
    public FacturaExtended getFacturaExtended(Long facturaId) {
        FacturaEntity entity = Optional.ofNullable(facturaDao.findOne(facturaId))
                .orElseThrow(() -> APIExceptions.objetoNoEncontrado(
                        messageSource.getMessage(MessageKeys.FACTURA_NOT_FOUND,
                                new String[]{String.valueOf(facturaId)},
                                getLocale())
                ));
        return getConverterService().convertTo(entity, FacturaExtended.class);
    }

    @Override
    public Factura getFactura(Long facturaId) {
        return Optional.ofNullable(findById(facturaId))
                .orElseThrow(() -> APIExceptions.objetoNoEncontrado(
                        messageSource.getMessage(MessageKeys.FACTURA_NOT_FOUND,
                                new String[]{String.valueOf(facturaId)},
                                getLocale())
                ));
    }

    @Override
    public List<FacturaExtended> getFacturas() {
        return getConverterService().convertTo(facturaDao.findAll(), FacturaExtended.class);
    }
}
