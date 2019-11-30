package com.heystyles.factura.core.dto;

import com.heystyles.factura.core.domain.Factura;
import com.heystyles.factura.core.domain.GestionProducto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class FacturaRequest {

    @NotNull
    @Valid
    private Factura factura;

    @Valid
    private List<GestionProducto> gestionProductos;

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public List<GestionProducto> getGestionProductos() {
        return gestionProductos;
    }

    public void setGestionProductos(List<GestionProducto> gestionProductos) {
        this.gestionProductos = gestionProductos;
    }
}
