package com.heystyles.factura.api.dao.impl;

import com.heystyles.common.types.Page;
import com.heystyles.factura.api.dao.AbonoCustonDao;
import com.heystyles.factura.api.entity.AbonoEntity;
import com.heystyles.factura.core.filter.AbonoFilter;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional(readOnly = true)
public class AbonoDaoImpl implements AbonoCustonDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Page<AbonoEntity> getPage(AbonoFilter filter) {
        Session session = entityManager.unwrap(org.hibernate.Session.class);

        Criteria totalCriteria = session.createCriteria(AbonoEntity.class);

        Criteria pageCriteria = session.createCriteria(AbonoEntity.class);

        applyWhere(totalCriteria, filter);
        applyWhere(pageCriteria, filter);

        applySort(totalCriteria, filter);
        applySort(pageCriteria, filter);

        queryCount(totalCriteria);
        queryPage(pageCriteria, filter.getPageNumber(), filter.getPageSize());

        Long total = (Long) totalCriteria.uniqueResult();
        List<AbonoEntity> entities = pageCriteria.list();

        return new Page<>(total, entities);
    }

    private void applyWhere(Criteria criteria, AbonoFilter filter) {
        if (filter.getFacturaId() != null) {
            criteria.add(Restrictions.eq(AbonoEntity.Attributes.FACTURA_ID, filter.getFacturaId()));
        }
        if (filter.getFacturaId() != null) {
            criteria.add(Restrictions.eq(AbonoEntity.Attributes.USUARIO_ID, filter.getUsuarioId()));
        }
        if (filter.getFechaInicial() != null) {
            criteria.add(Restrictions.ge(AbonoEntity.Attributes.CREATED_DATE, filter.getFechaFinal()));
        }
        if (filter.getFechaFinal() != null) {
            criteria.add(Restrictions.le(AbonoEntity.Attributes.CREATED_DATE, filter.getFechaFinal()));
        }
    }

    private void applySort(Criteria criteria, AbonoFilter filter) {

    }

    private void queryCount(Criteria criteria) {
        criteria.setProjection(Projections.count(AbonoEntity.Attributes.ID));
    }

    private void queryPage(Criteria criteria, Integer pageNumber, Integer pageSize) {
        if (pageNumber != null && pageSize != null) {
            criteria.setFirstResult(pageNumber * pageSize);
            criteria.setMaxResults(pageSize);
        }
    }

    @Override
    public Double sumAbonosByFacturaId(Long facturaId) {
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(AbonoEntity.class);
        criteria.add(Restrictions.eq(AbonoEntity.Attributes.FACTURA_ID, facturaId));
        criteria.setProjection(Projections.sum(AbonoEntity.Attributes.VALOR));
        return (Double) criteria.uniqueResult();
    }
}
