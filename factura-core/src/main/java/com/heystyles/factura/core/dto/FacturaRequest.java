package com.heystyles.factura.core.dto;

import com.heystyles.factura.core.domain.Factura;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class FacturaRequest {

    @NotNull
    @Valid
    private Factura factura;

    private List<Long> marcaProducto;

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public List<Long> getMarcaProducto() {
        return marcaProducto;
    }

    public void setMarcaProducto(List<Long> marcaProducto) {
        this.marcaProducto = marcaProducto;
    }
}
