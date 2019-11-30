package com.heystyles.factura.core.domain;

import com.heystyles.usuarios.core.domain.ProveedorExtended;

public class FacturaExtended {

    private Factura factura;

    private ProveedorExtended proveedor;

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public ProveedorExtended getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorExtended proveedor) {
        this.proveedor = proveedor;
    }
}
