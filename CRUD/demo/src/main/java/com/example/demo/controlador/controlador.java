package com.example.demo.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.interfaces.interfazTareaService.ITareaService;
import com.example.demo.modelo.tarea;



@Controller
@RequestMapping
@CrossOrigin(origins = "*")
public class controlador {
    
    @Autowired
    private ITareaService service; 

    @GetMapping("/listar")
    public String listar(Model model) {
        List<tarea>tareas = service.listar();
        model.addAttribute("tareas", tareas);
        return "index";
    }

    



}
