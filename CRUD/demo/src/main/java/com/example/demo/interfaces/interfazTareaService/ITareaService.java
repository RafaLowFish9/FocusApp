package com.example.demo.interfaces.interfazTareaService;

import java.util.List;
import java.util.Optional;

import com.example.demo.modelo.tarea;

public interface ITareaService {

    public List<tarea> listar();
    public tarea guardarTarea(tarea t);
    public void eliminarTarea(int id);
    public void editarTarea(int id);
    public Optional<tarea> obtenerTareaPorId(int id);

    
} 