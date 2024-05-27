package com.unmsm.alumnoservice.dto.response;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AlumnoDtoResponse {
    private String codAlumno;
    private String idUsuario;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombre;
    private Integer anioIngreso;
    private String situAcademica;
    private String correo;
    private float promedioPonderado;
}

