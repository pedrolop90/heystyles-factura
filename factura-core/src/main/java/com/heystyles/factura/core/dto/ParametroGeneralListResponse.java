package com.heystyles.factura.core.dto;

import com.heystyles.common.types.ListResponse;
import com.heystyles.factura.core.domain.ParametroGeneral;

import java.util.List;

public class ParametroGeneralListResponse extends ListResponse<ParametroGeneral> {

    public ParametroGeneralListResponse() {
    }

    public ParametroGeneralListResponse(List<ParametroGeneral> parametroGenerals) {
        super(parametroGenerals);
    }

    public ParametroGeneralListResponse(Long count, List<ParametroGeneral> parametroGenerals) {
        super(parametroGenerals, count);
    }
}
