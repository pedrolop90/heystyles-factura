package com.heystyles.factura.api.service;

import com.heystyles.producto.core.dto.MarcaProductoDto;

import java.util.List;

public interface GestionProductoService {

    void upsert(Long facturaId, List<Long> marcasProductos);

    List<MarcaProductoDto> findMarcaProductoByFacturaId(Long facturaId);
}
