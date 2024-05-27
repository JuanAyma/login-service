package com.unmsm.alumnoservice.dto.response;

import jakarta.annotation.Nullable;
import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GetDataAlumnoByCodDtoResponse {

    private String codigo;
    private BigDecimal planEstudio;
    private String escuelaAcademica;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String situacionAcademica;
    private String correoElectronico;
    @Nullable
    private Float promedioPonderado;
    @Nullable
    private String numeroTelefonico;

}
