package com.heystyles.factura.api.dao;

import com.heystyles.common.types.Page;
import com.heystyles.factura.api.entity.AbonoEntity;
import com.heystyles.factura.core.filter.AbonoFilter;

public interface AbonoCustonDao {

    Page<AbonoEntity> getPage(AbonoFilter filter);

}
