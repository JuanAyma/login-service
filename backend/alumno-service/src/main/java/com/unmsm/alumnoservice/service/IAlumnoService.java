package com.unmsm.alumnoservice.service;

import com.unmsm.alumnoservice.core.abstracts.IDataResult;
import com.unmsm.alumnoservice.dto.response.AlumnoDtoResponse;
import com.unmsm.alumnoservice.dto.response.GetCursosByCodAlumnoDtoResponse;
import com.unmsm.alumnoservice.dto.response.GetDataAlumnoByCodDtoResponse;

import java.util.List;

public interface IAlumnoService {

    IDataResult<AlumnoDtoResponse> getListAll();

    IDataResult<List<GetCursosByCodAlumnoDtoResponse>> getCursosByCodAlumno(String codAlumno);

    IDataResult<GetDataAlumnoByCodDtoResponse> getObtenerDataAlumnoByCod(String codAlumno);
}
