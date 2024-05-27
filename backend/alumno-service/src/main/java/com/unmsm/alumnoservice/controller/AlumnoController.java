package com.unmsm.alumnoservice.controller;

import com.unmsm.alumnoservice.core.abstracts.IDataResult;
import com.unmsm.alumnoservice.dto.response.AlumnoDtoResponse;
import com.unmsm.alumnoservice.dto.response.GetCursosByCodAlumnoDtoResponse;
import com.unmsm.alumnoservice.dto.response.GetDataAlumnoByCodDtoResponse;
import com.unmsm.alumnoservice.service.AlumnoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping(value = "api/v1/alumno")
@RequiredArgsConstructor
public class AlumnoController {

    private final AlumnoServiceImpl alumnoService;
    @GetMapping("/listall")
    public IDataResult<AlumnoDtoResponse> getListAll()
            throws ExecutionException, InterruptedException {
        return alumnoService.getListAll();
    }
    @GetMapping("/get_cursoById/{codAlumno}")
    public IDataResult<List<GetCursosByCodAlumnoDtoResponse>> getCursosByCodAlumno(@PathVariable("codAlumno") String codAlumno)
            throws ExecutionException, InterruptedException {
        return alumnoService.getCursosByCodAlumno(codAlumno);
    }

    @GetMapping("/get_datosAlumnoByCod/{codAlumno}")
    public IDataResult<GetDataAlumnoByCodDtoResponse> getObtenerDataAlumnoByCod(@PathVariable("codAlumno") String codAlumno)
            throws ExecutionException, InterruptedException {
        return alumnoService.getObtenerDataAlumnoByCod(codAlumno);
    }


}
