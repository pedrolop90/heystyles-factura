package com.heystyles.factura.core.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.heystyles.common.exception.InvalidEnumValueException;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum ParametroGeneralEnum {
    VALOR_IVA(1L);

    private Long id;

    ParametroGeneralEnum(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonValue
    public String getValue() {
        return name();
    }

    @JsonCreator
    public static ParametroGeneralEnum fromValue(String value) {
        if (value != null && value.isEmpty()) {
            return null;
        }
        for (ParametroGeneralEnum p : values()) {
            if (p.name().equals(value)) {
                return p;
            }
        }
        throw new InvalidEnumValueException("ParametroGeneralEnum", value);
    }

}
