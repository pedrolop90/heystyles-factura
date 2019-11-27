package com.heystyles.factura.api.dao;

import com.heystyles.factura.api.entity.FacturaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaDao extends JpaRepository<FacturaEntity, Long> {
}
