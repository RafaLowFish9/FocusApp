package com.example.demo.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.interfaces.interfazTareaService.ITareaService;
import com.example.demo.modelo.tarea;



@Controller
@RequestMapping("/tareas")
public class TareaController {
    
    @Autowired
    private ITareaService service; 

    @GetMapping("/listar")
    public String listar(Model model) {
        List<tarea>tareas = service.listar();
        model.addAttribute("tareas", tareas);
        return "index";
    }

    /*  @PostMapping("/guardar")
    public String guardarTarea(@ModelAttribute tarea tarea) {
        service.guardarTarea(tarea);
        return "redirect:/tareas/listar";  // Redirige a la página de listado después de agregar la tarea
    }*/

    
    
}
