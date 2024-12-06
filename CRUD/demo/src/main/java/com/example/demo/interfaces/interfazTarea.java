package com.example.demo.interfaces;


import com.example.demo.modelo.tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface interfazTarea extends JpaRepository<tarea, Integer>{

   
}