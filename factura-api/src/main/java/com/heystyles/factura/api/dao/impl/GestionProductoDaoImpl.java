package com.heystyles.factura.api.dao.impl;

import com.heystyles.factura.api.dao.GestionProductoCustomDao;
import com.heystyles.factura.api.entity.GestionProductoEntity;
import com.heystyles.factura.api.service.ParametroGeneralService;
import com.heystyles.factura.core.domain.ParametroGeneral;
import com.heystyles.factura.core.domain.ParametroGeneralEnum;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional(readOnly = true)
public class GestionProductoDaoImpl implements GestionProductoCustomDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ParametroGeneralService parametroGeneralService;

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

        Double valorResultante = 0d;
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(GestionProductoEntity.class);
        criteria.createAlias(GestionProductoEntity.Attributes.FACTURA,
                GestionProductoEntity.Attributes.FACTURA);
        criteria.add(Restrictions.eq(GestionProductoEntity.Attributes.FACTURA_ID, facturaId));
        criteria.setProjection(Projections.projectionList()
                .add(Projections.property(GestionProductoEntity.Attributes.VALOR))
                .add(Projections.property(GestionProductoEntity.Attributes.CANTIDAD))
                .add(Projections.property(GestionProductoEntity.Attributes.PORCENTAJE_DESCUENTO))
                .add(Projections.property(GestionProductoEntity.Attributes.FACTURA_PORCENTAJE_DESCUENTO))
        );
        List<Object[]> listResponse = criteria.list();
        double porcentajeDescuentoFactura = 0;
        double porcentajeIva = calcularIva();
        for (Object[] objects : listResponse) {
            Double valor = (Double) objects[0];
            Long cantidad = (Long) objects[1];
            Double porcentajeDescuento = (Double) objects[2];
            porcentajeDescuentoFactura = (Double) objects[3];
            valorResultante += calcularValorProducto(valor, cantidad, porcentajeDescuento);
        }

        Double valorDescuento = valorResultante - (valorResultante * porcentajeDescuentoFactura);

        Double valorIva = valorDescuento + (valorDescuento * porcentajeIva);
        return valorIva;
    }

    private Double calcularIva() {
        ParametroGeneral parametroGeneralIva = parametroGeneralService
                .getParametroGeneralByEnum(ParametroGeneralEnum.VALOR_IVA);
        return Double.parseDouble(parametroGeneralIva.getValor());
    }

    private Double calcularValorProducto(Double valor, Long cantidad, Double porcentajeDescuento) {
        Double cantidadParcial = valor * cantidad;
        return cantidadParcial - (cantidadParcial * porcentajeDescuento);
    }

}
