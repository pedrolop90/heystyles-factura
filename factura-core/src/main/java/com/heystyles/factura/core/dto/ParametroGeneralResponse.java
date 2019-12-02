package com.heystyles.factura.core.dto;

import com.heystyles.common.types.ObjectResponse;
import com.heystyles.factura.core.domain.ParametroGeneral;

public class ParametroGeneralResponse extends ObjectResponse<ParametroGeneral> {

    public ParametroGeneralResponse() {
    }

    public ParametroGeneralResponse(ParametroGeneral parametroGeneral) {
        super(parametroGeneral);
    }
}
