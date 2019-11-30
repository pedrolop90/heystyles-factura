package com.heystyles.factura.api.dao.impl;

import com.heystyles.factura.api.dao.GestionProductoCustomDao;
import com.heystyles.factura.api.entity.GestionProductoEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional(readOnly = true)
public class GestionProductoDaoImpl implements GestionProductoCustomDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Long> findMarcaProductoIdByFacturaId(Long facturaId) {
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(GestionProductoEntity.class);
        criteria.add(Restrictions.eq(GestionProductoEntity.Attributes.FACTURA_ID, facturaId));
        criteria.setProjection(Projections.property(GestionProductoEntity.Attributes.MARCA_PRODUCTO_ID));
        return criteria.list();
    }

    @Override
    public Double valorTotalGestionProductoByFacturaId(Long facturaId) {
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(GestionProductoEntity.class);
        criteria.add(Restrictions.eq(GestionProductoEntity.Attributes.FACTURA_ID, facturaId));
        criteria.setProjection(Projections.sum(GestionProductoEntity.Attributes.VALOR));
        return (Double) criteria.uniqueResult();
    }
}
