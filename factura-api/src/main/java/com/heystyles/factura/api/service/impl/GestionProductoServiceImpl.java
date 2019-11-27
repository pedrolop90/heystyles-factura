package com.heystyles.factura.api.service.impl;

import com.heystyles.factura.api.dao.GestionProductoDao;
import com.heystyles.factura.api.entity.FacturaEntity;
import com.heystyles.factura.api.entity.GestionProductoEntity;
import com.heystyles.factura.api.service.GestionProductoService;
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

    @Override
    public void upsert(Long facturaId, List<Long> marcasProductos) {
        if (marcasProductos == null) {
            return;
        }
        List<GestionProductoEntity> existing = gestionProductoDao.findByFacturaId(facturaId);

        List<GestionProductoEntity> toDelete = new ArrayList<>();
        List<GestionProductoEntity> toSave = new ArrayList<>();

        Set<Long> oldMarcasIds = existing
                .stream()
                .map(e -> e.getMarcaProductoId())
                .collect(Collectors.toSet());

        existing.stream()
                .filter(p -> !marcasProductos.contains(p.getMarcaProductoId()))
                .forEach(p -> toDelete.add(p));

        marcasProductos.stream()
                .filter(l -> !oldMarcasIds.contains(l))
                .forEach(l -> {
                    GestionProductoEntity entity = new GestionProductoEntity();
                    entity.setFactura(new FacturaEntity(facturaId));
                    entity.setMarcaProductoId(l);
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
}
