package com.heystyles.factura.api.service;

import com.heystyles.common.service.Service;
import com.heystyles.factura.core.domain.Abono;
import com.heystyles.factura.core.dto.AbonoListResponse;
import com.heystyles.factura.core.filter.AbonoFilter;

public interface AbonoService extends Service<Abono, Long> {

    Abono getAbono(Long abonoId);

    AbonoListResponse getFilter(AbonoFilter filter);
}
