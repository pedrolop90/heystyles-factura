package com.heystyles.factura.core.dto;

import com.heystyles.common.types.ListResponse;
import com.heystyles.factura.core.domain.Abono;

import java.util.List;

public class AbonoListResponse extends ListResponse<Abono> {

    public AbonoListResponse() {
    }

    public AbonoListResponse(List<Abono> abonos) {
        super(abonos);
    }

    public AbonoListResponse(Long count, List<Abono> abonos) {
        super(abonos, count);
    }
}
