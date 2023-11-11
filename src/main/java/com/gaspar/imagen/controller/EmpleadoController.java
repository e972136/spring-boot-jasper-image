package com.gaspar.imagen.controller;

import com.gaspar.imagen.entity.Empleado;
import com.gaspar.imagen.service.EmpleadoService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class EmpleadoController {
    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping("/empleado")
    public List<Empleado> findAll(){
        return empleadoService.findAll();
    }

    @GetMapping("/reporte")
    public String generar() throws JRException, FileNotFoundException {
        return empleadoService.exportReport("pdf");
    }
}
