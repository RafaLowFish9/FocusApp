package com.example.demo.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.interfaces.interfazTarea;
import com.example.demo.interfaces.interfazTareaService.ITareaService;
import com.example.demo.modelo.tarea;

@Service
public class serviciosTarea implements ITareaService {

    @Autowired
    private interfazTarea datos;

    @GetMapping
    public List<tarea> listar() {
        
        return (List<tarea>)datos.findAll();
   }

    @PostMapping
    public tarea guardarTarea(tarea t) {
       
        return datos.save(t);
    }

    @DeleteMapping("/{id}")
    public void eliminarTarea(int id) {
        datos.deleteById(id);
    }

    @Override
    public Optional<tarea> obtenerTareaPorId(int id) {
        return datos.findById(id);
    }
    

    @Override
    public void editarTarea(int id) {
    
        throw new UnsupportedOperationException("Unimplemented method 'editarTarea'");
    }    
    
}
