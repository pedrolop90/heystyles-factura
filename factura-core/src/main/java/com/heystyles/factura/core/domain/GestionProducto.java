package com.heystyles.factura.core.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.heystyles.common.types.DomainBean;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GestionProducto extends DomainBean<Long> {

    private Long id;

    @NotNull
    private Long marcaProductoId;

    @NotNull
    @Min(value = 0)
    private double valor;

    @NotNull
    @Min(value = 0)
    private long cantidad;

    private EstadoEntrada estadoEntrada;

    @Min(value = 0)
    @Max(value = 100)
    private double porcentajeDescuento;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getMarcaProductoId() {
        return marcaProductoId;
    }

    public void setMarcaProductoId(Long marcaProductoId) {
        this.marcaProductoId = marcaProductoId;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public EstadoEntrada getEstadoEntrada() {
        return estadoEntrada;
    }

    public void setEstadoEntrada(EstadoEntrada estadoEntrada) {
        this.estadoEntrada = estadoEntrada;
    }

    public Double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(Double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }
}
