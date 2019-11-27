package com.heystyles.factura.core.domain;

import com.heystyles.usuarios.core.domain.Proveedor;

public class FacturaExtended {

    private Factura factura;

    private Proveedor proveedor;

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}
