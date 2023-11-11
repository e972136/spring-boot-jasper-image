package com.gaspar.imagen.controller;

import com.gaspar.imagen.entity.Empleado;
import com.gaspar.imagen.service.EmpleadoService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class EmpleadoController {
    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping(value = "/empleado",produces = "application/json")
    public ResponseEntity<List<Empleado>> findAll(){
        return ResponseEntity.ok(empleadoService.findAll());
    }

    @GetMapping("/reporte_imagen_bd/{company}")
    public String generarBD(
            @PathVariable int company
    ) throws JRException, FileNotFoundException {
        return empleadoService.exportReportBD(company,"pdf");
    }

    @GetMapping("/reporte_imagen_local")
    public String generarLocal() throws JRException, FileNotFoundException {
        return empleadoService.exportReportLocal("pdf");
    }
}
