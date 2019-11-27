package com.heystyles.factura.core.domain;

import com.heystyles.producto.core.dto.MarcaProductoDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class GestionProductoExtended {

    @NotNull
    @Valid
    private GestionProducto gestionProducto;

    private List<MarcaProductoDto> marcasProductos;

    public GestionProducto getGestionProducto() {
        return gestionProducto;
    }

    public void setGestionProducto(GestionProducto gestionProducto) {
        this.gestionProducto = gestionProducto;
    }

    public List<MarcaProductoDto> getMarcasProductos() {
        return marcasProductos;
    }

    public void setMarcasProductos(List<MarcaProductoDto> marcasProductos) {
        this.marcasProductos = marcasProductos;
    }
}
