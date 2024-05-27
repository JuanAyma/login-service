package com.unmsm.alumnoservice.repository;

import com.unmsm.alumnoservice.dto.response.GetCursosByCodAlumnoDtoResponse;
import com.unmsm.alumnoservice.dto.response.GetDataAlumnoByCodDtoResponse;
import com.unmsm.alumnoservice.entity.AlumnoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface AlumnoRepository extends JpaRepository<AlumnoEntity,String> {
//procedimiento almacenado para obtener los cursos de un estudiante.
    @Query (value = "call SP_GET_CURSOS_BY_COD_ALUMNO(:parametro)", nativeQuery = true)
    List<Object[]> getCursosByCodAlumno(@Param("parametro") String parametro);
    default List<GetCursosByCodAlumnoDtoResponse> SP_GET_CURSOS_BY_COD_ALUMNO(String parametro) {
        List<Object[]> results = getCursosByCodAlumno(parametro);

        return results.stream()
                .map(result -> {
                    GetCursosByCodAlumnoDtoResponse response = new GetCursosByCodAlumnoDtoResponse();
                    response.setCodigoAlumno((String) result[0]);
                    response.setNombreAlumno((String) result[1]);
                    response.setApellidoPaterno((String) result[2]);
                    response.setApellidoMaterno((String) result[3]);
                    response.setCodigoAsignatura((String) result[4]);
                    response.setNombreAsignatura((String) result[5]);
                    response.setCreditos((int) result[6]);
                    response.setSeccion((String) result[7]);
                    response.setHorario((String) result[8]);
                    response.setCuposAsignados((BigDecimal) result[9]);
                    response.setCuposOcupados((BigDecimal) result[10]);
                    return response;
                }).collect(Collectors.toList());
    }

    @Query(value = "call SP_GET_DATA_BY_COD_ALUMNO(:parametro)", nativeQuery = true)
    List<Object[]> getDataAlumnoByCod(@Param("parametro") String parametro);
    default GetDataAlumnoByCodDtoResponse SP_GET_DATA_BY_COD_ALUMNO(String parametro) {
        //List<Object[]> results = getDataAlumnoByCod(parametro);
        List<Object[]> results = getDataAlumnoByCod(parametro);

        Object[] result = results.get(0);
        GetDataAlumnoByCodDtoResponse response = new GetDataAlumnoByCodDtoResponse();

        response.setCodigo((String) result[0]);
        response.setPlanEstudio((BigDecimal) result[1]);
        response.setEscuelaAcademica((String) result[2]);
        response.setNombres((String) result[3]);
        response.setApellidoPaterno((String) result[4]);
        response.setApellidoMaterno((String) result[5]);
        response.setSituacionAcademica((String) result[6]);
        response.setCorreoElectronico((String) result[7]);
        response.setPromedioPonderado(null);
        response.setNumeroTelefonico(null);

        return response;
    }

}
