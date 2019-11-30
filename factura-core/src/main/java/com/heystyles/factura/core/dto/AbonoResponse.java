package com.heystyles.factura.core.dto;

import com.heystyles.common.types.ObjectResponse;
import com.heystyles.factura.core.domain.Abono;

public class AbonoResponse extends ObjectResponse<Abono> {

    public AbonoResponse() {
    }

    public AbonoResponse(Abono abono) {
        super(abono);
    }
}
