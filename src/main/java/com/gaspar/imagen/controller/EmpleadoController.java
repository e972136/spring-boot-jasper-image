package com.gaspar.imagen.controller;

import com.gaspar.imagen.entity.Empleado;
import com.gaspar.imagen.service.EmpleadoService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

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

    @GetMapping("/hacer")
    public ResponseEntity<Resource> descargar(){
        String directorioArchivos = "Q:\\Descargas\\";

        // Crear el objeto File para el archivo
        File archivo = new File(directorioArchivos + "pizzaty.pdf");

        // Verificar si el archivo existe
        if (!archivo.exists()) {
            return ResponseEntity.notFound().build();
        }

        // Cargar el archivo como recurso FileSystemResource
        Resource recurso = new FileSystemResource(archivo);

        // Configurar las cabeceras de la respuesta para indicar que es una descarga de archivo
        HttpHeaders cabeceras = new HttpHeaders();
        cabeceras.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + "pizzaty.pdf");
        cabeceras.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);

        // Devolver el recurso como ResponseEntity
        return ResponseEntity.ok().headers(cabeceras).body(recurso);
    }
}
