package com.heystyles.factura.core.dto;

import com.heystyles.common.types.ListResponse;
import com.heystyles.factura.core.domain.FacturaExtended;

import java.util.List;

public class FacturaExtendedListResponse extends ListResponse<FacturaExtended> {

    public FacturaExtendedListResponse() {
    }

    public FacturaExtendedListResponse(List<FacturaExtended> facturaExtendedList) {
        super(facturaExtendedList);
    }
}
