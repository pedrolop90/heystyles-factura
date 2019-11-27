package com.heystyles.factura.core.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.heystyles.common.exception.InvalidEnumValueException;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum EstadoEntrada {

    MAL_ESTADO, ESTRAVIADO, BUEN_ESTADO;

    @JsonValue
    public String getValue() {
        return name();
    }

    @JsonCreator
    public static EstadoEntrada fromValue(String value) {
        if (value != null && value.isEmpty()) {
            return null;
        }
        for (EstadoEntrada p : values()) {
            if (p.name().equals(value)) {
                return p;
            }
        }
        throw new InvalidEnumValueException("EstadoEntrada", value);
    }

}
