package com.unmsm.alumnoservice.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "alumno")
public class AlumnoEntity {

  @Id
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
