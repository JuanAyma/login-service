package com.unmsm.alumnoservice.service;

import com.unmsm.alumnoservice.common.HttpStatusCodes;
import com.unmsm.alumnoservice.core.abstracts.IDataResult;
import com.unmsm.alumnoservice.core.results.ErrorDataResult;
import com.unmsm.alumnoservice.core.results.SuccessDataResult;
import com.unmsm.alumnoservice.dto.response.AlumnoDtoResponse;
import com.unmsm.alumnoservice.dto.response.GetCursosByCodAlumnoDtoResponse;
import com.unmsm.alumnoservice.dto.response.GetDataAlumnoByCodDtoResponse;
import com.unmsm.alumnoservice.entity.AlumnoEntity;
import com.unmsm.alumnoservice.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



@Service
public class AlumnoServiceImpl implements IAlumnoService{
    private static final Logger LOGGER = Logger.getLogger(AlumnoServiceImpl.class.getName());
    @Autowired
    private AlumnoRepository alumnoRepository;
    @Override
    public IDataResult<AlumnoDtoResponse> getListAll() {
         List<AlumnoDtoResponse>  listAlumnoDtoResponse = new ArrayList<>();
         List<AlumnoEntity> listalumnoEntity  = new ArrayList<>();
         try {
            listalumnoEntity = alumnoRepository.findAll();

            listalumnoEntity.forEach( alumnoEntity -> {
                AlumnoDtoResponse alumnoDtoResponse = AlumnoDtoResponse.builder()
                        .codAlumno(alumnoEntity.getCodAlumno())
                        .idUsuario(alumnoEntity.getIdUsuario())
                        .apellidoPaterno(alumnoEntity.getApellidoPaterno())
                        .apellidoMaterno(alumnoEntity.getApellidoMaterno())
                        .nombre(alumnoEntity.getNombre())
                        .anioIngreso(alumnoEntity.getAnioIngreso())
                        .situAcademica(alumnoEntity.getSituAcademica())
                        .correo(alumnoEntity.getCorreo())
                        .promedioPonderado(alumnoEntity.getPromedioPonderado())
                        .build();

                listAlumnoDtoResponse.add(alumnoDtoResponse);
            });
         }catch(Exception e) {
            LOGGER.log(Level.SEVERE,e.getMessage(),e);
            return new ErrorDataResult<>(null, HttpStatusCodes.HTTP_INTERNAL_SERVER_ERROR, e.getMessage());
         }
//        return null;
        return new SuccessDataResult(listAlumnoDtoResponse, "lista consultada correctamente");

    }

    @Override
    public IDataResult<List<GetCursosByCodAlumnoDtoResponse>> getCursosByCodAlumno(String codAlumno) {
        List<GetCursosByCodAlumnoDtoResponse> lista = new ArrayList<>();
        try {
            lista = alumnoRepository.SP_GET_CURSOS_BY_COD_ALUMNO(codAlumno);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return new ErrorDataResult<>(null, HttpStatusCodes.HTTP_INTERNAL_SERVER_ERROR, e.getMessage());
        }
//        return null;
        return new SuccessDataResult(lista, "lista consultada correctamente");
    }

    @Override
    public IDataResult<GetDataAlumnoByCodDtoResponse> getObtenerDataAlumnoByCod(String codAlumno) {
        GetDataAlumnoByCodDtoResponse dataAlumno = null;
        try {
            dataAlumno = alumnoRepository.SP_GET_DATA_BY_COD_ALUMNO(codAlumno);

        }catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return new ErrorDataResult<>(null, HttpStatusCodes.HTTP_INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return new SuccessDataResult(dataAlumno, "Data del Alumno mostrada correctamente");
    }
}



