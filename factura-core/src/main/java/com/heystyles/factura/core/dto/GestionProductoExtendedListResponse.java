package com.heystyles.factura.core.dto;

import com.heystyles.common.types.ListResponse;
import com.heystyles.factura.core.domain.GestionProductoExtended;

import java.util.List;

public class GestionProductoExtendedListResponse extends ListResponse<GestionProductoExtended> {

    public GestionProductoExtendedListResponse() {
    }

    public GestionProductoExtendedListResponse(List<GestionProductoExtended> gestionProductoExtendeds) {
        super(gestionProductoExtendeds);
    }
}
