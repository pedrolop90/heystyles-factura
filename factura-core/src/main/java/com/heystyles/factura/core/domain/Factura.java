package com.heystyles.factura.core.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.heystyles.common.types.DomainBean;
import com.heystyles.common.util.Constants;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Factura extends DomainBean<Long> {

    private Long id;

    @NotNull
    private Long proveedorId;

    private Double valorTotal;

    private Double porcentaje;

    private Double porcentajeDescuento;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.FORMAT_DATE_TIME)
    private LocalDateTime fechaLimitePago;

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

    public Double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(Double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public LocalDateTime getFechaLimitePago() {
        return fechaLimitePago;
    }

    public void setFechaLimitePago(LocalDateTime fechaLimitePago) {
        this.fechaLimitePago = fechaLimitePago;
    }

    public boolean isfPago() {
        return fPago;
    }

    public void setfPago(boolean fPago) {
        this.fPago = fPago;
    }
}
