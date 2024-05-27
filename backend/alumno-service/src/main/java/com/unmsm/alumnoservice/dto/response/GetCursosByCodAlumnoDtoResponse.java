package com.unmsm.alumnoservice.dto.response;

import lombok.*;

import java.math.BigDecimal;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GetCursosByCodAlumnoDtoResponse {

    private String codigoAlumno;
    private String nombreAlumno;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String codigoAsignatura;
    private String nombreAsignatura;
    private int creditos;
    private String seccion;
    private String horario;
    private BigDecimal cuposAsignados;
    private BigDecimal cuposOcupados;

}
