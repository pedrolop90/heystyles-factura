package com.heystyles.factura.api.dao;

import com.heystyles.factura.api.entity.ParametroGeneralEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametroGeneralDao
        extends JpaRepository<ParametroGeneralEntity, Long> {

    ParametroGeneralEntity findByNombre(String nombre);

}
