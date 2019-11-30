package com.heystyles.factura.api.service;

import com.heystyles.factura.core.domain.GestionProducto;
import com.heystyles.factura.core.domain.GestionProductoExtended;
import com.heystyles.producto.core.dto.MarcaProductoDto;

import java.util.List;

public interface GestionProductoService {

    void upsert(Long facturaId, List<GestionProducto> gestionProductos);

    List<MarcaProductoDto> findMarcaProductoByFacturaId(Long facturaId);

    List<GestionProductoExtended> getGestionProductoExtendedByFacturaId(Long facturaId);

    GestionProductoExtended getGestionProductoExtended(Long gestionProductoId);
}
