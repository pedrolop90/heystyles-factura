package com.heystyles.factura.core.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.heystyles.common.types.DomainBean;
import com.heystyles.common.util.Constants;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Factura extends DomainBean<Long> {

    private Long id;

    @NotNull
    private Long proveedorId;

    @Min(value = 0)
    private double valorTotal;

    @Min(value = 0)
    @Max(value = 100)
    private double porcentajeDescuento;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.FORMAT_DATE)
    private LocalDate fechaLimitePago;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.FORMAT_DATE)
    private LocalDate fechaCreacion;

    private boolean fPago;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Long proveedorId) {
        this.proveedorId = proveedorId;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(Double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public LocalDate getFechaLimitePago() {
        return fechaLimitePago;
    }

    public void setFechaLimitePago(LocalDate fechaLimitePago) {
        this.fechaLimitePago = fechaLimitePago;
    }

    public boolean isfPago() {
        return fPago;
    }

    public void setfPago(boolean fPago) {
        this.fPago = fPago;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
