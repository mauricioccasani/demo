package com.example.demo.dto;

import java.util.Arrays;

public enum DocumentoTipoEnum {

    DNI("01", "DNI", "^[0-9]{8}$"),
    RUC("02", "RUC", "^[0-9]{10}$");
    private String codigo;
    private String descripcion;
    private String pattern;

    DocumentoTipoEnum(String codigo, String descripcion, String pattern) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.pattern = pattern;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPattern() {
        return pattern;
    }

    public static DocumentoTipoEnum getCodigo(String code) {
        return Arrays.stream(values()).filter(documentoTipoEnum -> documentoTipoEnum.getDescripcion().equals(code)).findAny().orElse(null);
    }
}
