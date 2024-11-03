package com.focus.focusDemo.tarea;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TareaServicio {

    private final TareaRepositorio tareaRepo;     

    public void createTarea(Tarea tarea)
    {
        tareaRepo.save(tarea);
    }

    public List<Tarea> listaTareas()
    {
        return (List<Tarea>)tareaRepo.findAll();
    }

}
