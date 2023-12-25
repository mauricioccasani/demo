package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TipoDocumentoDto {
    private String codigo;
    private String persona;
    private String genero;
    private String dni;
    private String estado;
}
