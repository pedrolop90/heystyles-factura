package com.heystyles.factura.api.dao;

import com.heystyles.factura.api.entity.GestionProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GestionProductoDao
        extends JpaRepository<GestionProductoEntity, Long>, GestionProductoCustomDao {

    List<GestionProductoEntity> findByFacturaId(Long facturaId);

}
