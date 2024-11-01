package com.example.demo.interfaces.interfazTareaService;

import java.util.List;

import com.example.demo.modelo.tarea;

public interface ITareaService {

    public List<tarea> listar();
    public int  guardarTarea(tarea t);
    public void eliminarTarea(int id);
    public void editarTarea(int id);
    
} 