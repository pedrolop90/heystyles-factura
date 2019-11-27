package com.heystyles.factura.api.dao;

import java.util.List;

public interface GestionProductoCustomDao {

    List<Long> findMarcaProductoIdByFacturaId(Long facturaId);
}
