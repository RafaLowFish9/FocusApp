package com.example.demo.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.interfaces.interfazTarea;
import com.example.demo.interfaces.interfazTareaService.ITareaService;
import com.example.demo.modelo.tarea;

@Service
public class serviciosTarea implements ITareaService {

    @Autowired
    private interfazTarea datos;

    @Override
    public List<tarea> listar() {
        // TODO Auto-generated method stub
        return (List<tarea>)datos.findAll();
   }

    @Override
    public int guardarTarea(tarea t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardarTarea'");
    }

    @Override
    public void eliminarTarea(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarTarea'");
    }

    @Override
    public void editarTarea(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editarTarea'");
    }

    
    
}
