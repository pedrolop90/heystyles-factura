package com.heystyles.factura.core.dto;

import com.heystyles.common.types.ObjectResponse;
import com.heystyles.factura.core.domain.FacturaExtended;

public class FacturaExtendedResponse extends ObjectResponse<FacturaExtended> {

    public FacturaExtendedResponse() {
    }

    public FacturaExtendedResponse(FacturaExtended facturaExtended) {
        super(facturaExtended);
    }
}
