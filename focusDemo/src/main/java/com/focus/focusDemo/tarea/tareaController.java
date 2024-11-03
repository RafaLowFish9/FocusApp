package com.focus.focusDemo.tarea;

import org.springframework.beans.factory.annotation.Autowired;

//import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/Tarea")
@RequiredArgsConstructor

public class tareaController {

    @Autowired
    private final TareaServicio tareaServicio;
    
    @GetMapping
    public void listaTareas()
    {
        tareaServicio.listaTareas(); 
    }

    @PostMapping
    public void createTarea(@RequestBody Tarea tarea)
    {
    tareaServicio.createTarea(tarea);
    }

   
}
