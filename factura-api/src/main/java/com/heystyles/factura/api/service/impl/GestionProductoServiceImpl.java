package com.heystyles.factura.api.service.impl;

import com.heystyles.common.service.ConverterService;
import com.heystyles.factura.api.dao.GestionProductoDao;
import com.heystyles.factura.api.entity.FacturaEntity;
import com.heystyles.factura.api.entity.GestionProductoEntity;
import com.heystyles.factura.api.service.GestionProductoService;
import com.heystyles.factura.core.domain.GestionProducto;
import com.heystyles.factura.core.domain.GestionProductoExtended;
import com.heystyles.producto.cliente.MarcaProductoClient;
import com.heystyles.producto.core.dto.MarcaProductoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GestionProductoServiceImpl implements GestionProductoService {

    @Autowired
    private GestionProductoDao gestionProductoDao;

    @Autowired
    private MarcaProductoClient marcaProductoClient;

    @Autowired
    private ConverterService converterService;

    @Override
    public void upsert(Long facturaId, List<GestionProducto> gestionProductos) {
        if (gestionProductos == null) {
            return;
        }
        List<GestionProductoEntity> existing = gestionProductoDao.findByFacturaId(facturaId);

        List<GestionProductoEntity> toDelete = new ArrayList<>();
        List<GestionProductoEntity> toSave = new ArrayList<>();

        Set<Long> oldMarcasIds = existing
                .stream()
                .map(e -> e.getMarcaProductoId())
                .collect(Collectors.toSet());

        Set<Long> marcasProductosIds = gestionProductos
                .stream()
                .map(e -> e.getMarcaProductoId())
                .collect(Collectors.toSet());

        existing.stream()
                .filter(p -> !marcasProductosIds.contains(p.getMarcaProductoId()))
                .forEach(p -> toDelete.add(p));

        gestionProductos.stream()
                .filter(l -> !oldMarcasIds.contains(l.getMarcaProductoId()))
                .forEach(l -> {
                    GestionProductoEntity entity = converterService.convertTo(l, GestionProductoEntity.class);
                    entity.setFactura(new FacturaEntity(facturaId));
                    toSave.add(entity);
                });

        gestionProductoDao.delete(toDelete);
        gestionProductoDao.save(toSave);
    }

    @Override
    public List<MarcaProductoDto> findMarcaProductoByFacturaId(Long facturaId) {
        List<Long> marcasProductosIds = gestionProductoDao.findMarcaProductoIdByFacturaId(facturaId);
        return marcaProductoClient.findMarcaProductosById(marcasProductosIds);
    }

    @Override
    public List<GestionProductoExtended> getGestionProductoExtendedByFacturaId(Long facturaId) {
        List<GestionProductoEntity> gestionProductoEntities = gestionProductoDao.findByFacturaId(facturaId);
        return converterService.convertTo(gestionProductoEntities, GestionProductoExtended.class);
    }

    @Override
    public GestionProductoExtended getGestionProductoExtended(Long gestionProductoId) {
        GestionProductoEntity gestionProductoEntity = gestionProductoDao.findOne(gestionProductoId);
        return converterService.convertTo(gestionProductoEntity, GestionProductoExtended.class);
    }
}
