package com.example.demo.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.interfaces.interfazTareaService.ITareaService;
import com.example.demo.modelo.tarea;

@RestController
@RequestMapping("/api/tareas")
public class TareaRestControlador {

    @Autowired
    private ITareaService service;

    @GetMapping
    public List<tarea> listar() {
        return service.listar();
    }

    @PostMapping
    public tarea guardar(@RequestBody tarea nuevaTarea) {
        return service.guardarTarea(nuevaTarea);  
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
       // Verificar si existe la tarea
        if (id != 0) {
            service.eliminarTarea(id); // Lógica para eliminar la tarea
            return ResponseEntity.noContent().build(); // Respuesta HTTP 204 (sin contenido)
        } else {
            return ResponseEntity.notFound().build(); // Respuesta HTTP 404 (no encontrado)
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<tarea> actualizarEstado(@PathVariable int id, @RequestBody tarea tareaActualizada) {
    Optional<tarea> tareaExistente = service.obtenerTareaPorId(id);
    if (tareaExistente.isPresent()) {
        tarea tareaModificada = tareaExistente.get();
        tareaModificada.setEstado(tareaActualizada.getEstado());
        service.guardarTarea(tareaModificada);
        return ResponseEntity.ok(tareaModificada);
    } else {
        return ResponseEntity.notFound().build();
    }
}


   


}
