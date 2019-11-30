package com.heystyles.factura.api.dao;

import com.heystyles.factura.api.entity.AbonoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbonoDao
        extends JpaRepository<AbonoEntity, Long>, AbonoCustonDao {
}
