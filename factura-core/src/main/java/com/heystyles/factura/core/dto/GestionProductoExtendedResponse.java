package com.heystyles.factura.core.dto;

import com.heystyles.common.types.ObjectResponse;
import com.heystyles.factura.core.domain.GestionProductoExtended;

public class GestionProductoExtendedResponse extends ObjectResponse<GestionProductoExtended> {

    public GestionProductoExtendedResponse() {
    }

    public GestionProductoExtendedResponse(GestionProductoExtended gestionProducto) {
        super(gestionProducto);
    }
}
