package com.gaspar.imagen.repository;

import com.gaspar.imagen.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado,Integer> {
}
