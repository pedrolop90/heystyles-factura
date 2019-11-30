package com.heystyles.factura.api.service;

import com.heystyles.common.service.Service;
import com.heystyles.factura.core.domain.Factura;
import com.heystyles.factura.core.domain.FacturaExtended;
import com.heystyles.factura.core.dto.FacturaRequest;

import java.util.List;

public interface FacturaService extends Service<Factura, Long> {

    Long insert(FacturaRequest request);

    void update(FacturaRequest request);

    FacturaExtended getFacturaExtended(Long facturaId);

    Factura getFactura(Long facturaId);

    List<FacturaExtended> getFacturas();
}
