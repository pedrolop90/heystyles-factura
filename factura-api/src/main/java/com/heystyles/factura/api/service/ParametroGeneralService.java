package com.heystyles.factura.api.service;

import com.heystyles.common.service.Service;
import com.heystyles.factura.core.domain.ParametroGeneral;
import com.heystyles.factura.core.domain.ParametroGeneralEnum;

public interface ParametroGeneralService extends Service<ParametroGeneral, Long> {

    ParametroGeneral getParametroGeneral(Long parametroGeneralId);

    ParametroGeneral getParametroGeneralByEnum(ParametroGeneralEnum parametroGeneralEnum);

}
