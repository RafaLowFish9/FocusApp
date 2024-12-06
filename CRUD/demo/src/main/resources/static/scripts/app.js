const API_URL = "/api/tareas";


document.addEventListener("DOMContentLoaded", () => {
const contenedorTarea = document.getElementsByClassName('contenedor-tareas');
const btnBorrarTarea = document.getElementById('btnBorrarTarea');
const puntosPorTarea = 10;
 //Puntos del usuario
 let puntosAcumulados = 0;
//Elemento donde se muestran los puntos
const puntosUsuario = document.getElementById('points');

document.getElementById("btnAgregarTarea").addEventListener("click", () => {
    const titulo = prompt("Introduzca el nombre de la tarea");
    const descripcion = prompt("Introduzca una descripción de la tarea");
    const fecha_limite = prompt("Introduzca una fecha límite");
    let estado = "Por hacer";
   
    
   

    if(titulo && descripcion) {
        fetch(API_URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ titulo, descripcion, fecha_limite, estado, puntosPorTarea,completada: false})
        }) 
        .then(response => response.json())
        .then(tarea => {
            console.log("Tarea añadida:", tarea);
            cargarTareas();
            });
        }
    }); 

    function cargarTareas() {
        fetch(API_URL)
            .then(response => response.json())
            .then(tareas => {
                const contenedorPorHacer = document.getElementById('tareasPorHacer');
                const contenedorHechas = document.getElementById('tareasHechas');
                
                contenedorPorHacer.innerHTML = "";
                contenedorHechas.innerHTML = "";
    
                tareas.forEach(tarea => {
                    const tareaHtml = crearTareaHtml(tarea);
                    if (tarea.estado === "hecha") {
                        contenedorHechas.appendChild(tareaHtml);
                    } else {
                        contenedorPorHacer.appendChild(tareaHtml);
                    }
                });
            })
            .catch(error => console.error('Error al cargar las tareas:', error));
    }
    
    function crearTareaHtml(tarea) {
        const tareaItem = document.createElement('div');
        tareaItem.dataset.taskId = tarea.id; // Asegura el ID de la tarea
    
        tareaItem.innerHTML = `
            <div class="tarea-item"  data-id-tarea = "${tarea.id}">
                        <div class="tarea-icono">
                            <img src="/imagenes/libros.png" alt="Icono de tarea" />
                        </div>
                        <div class="tarea-contenido">
                            <h3>${tarea.titulo}</h3>
                            <p>${tarea.descripcion}</p>
                            <p>${tarea.estado}</p>
                            <p>${tarea.fecha_limite}</p>
                        </div>
                        <div class="tarea-acciones">
                            <button class="marcar-realizada" data-id="${tarea.id}">Marcar como realizada</button>
                            <input type="checkbox">
                        </div>
                    </div>
                `;
    
        // Agregar evento al botón de "Marcar como realizada"
        const botonMarcarRealizada = tareaItem.querySelector('.marcar-realizada');
        botonMarcarRealizada.addEventListener('click', () => moverTarea(tareaItem, tarea));
    
        return tareaItem;
    }
    
    function moverTarea(tareaItem, tarea) {
        const contenedorPorHacer = document.getElementById('tareasPorHacer');
        const contenedorHechas = document.getElementById('tareasHechas');
    
        // Cambiar estado de la tarea
        const nuevoEstado = tarea.estado === "hecha" ? "por_hacer" : "hecha";
        tarea.estado = nuevoEstado;
    
        // Mover visualmente la tarea
        if (nuevoEstado === "hecha") {
            contenedorHechas.appendChild(tareaItem);
            puntosAcumulados += puntosPorTarea;
            puntosUsuario.textContent = `Puntos: ${puntosAcumulados}`
        } else {
            contenedorPorHacer.appendChild(tareaItem);
        }
    
        // Actualizar el estado de la tarea en el servidor
        actualizarEstadoTarea(tarea);
    }
    
    function actualizarEstadoTarea(tarea) {
        fetch(`${API_URL}/${tarea.id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(tarea)
        })
        .then(response => {
            if (!response.ok) {
                console.error('Error al actualizar el estado de la tarea:', response.statusText);
            }
        })
        .catch(error => console.error('Error de conexión al servidor:', error));
    }
    

cargarTareas();


btnBorrarTarea.addEventListener('click', async () => {
    // Obtener todos los contenedores de tareas
    const contenedoresTareas = document.getElementsByClassName('contenedor-tareas');
    const tareasMarcadas = [];

    // Recorrer cada contenedor y buscar los checkboxes seleccionados
    for (const contenedor of contenedoresTareas) {
        const checkboxes = contenedor.querySelectorAll('.tarea-acciones input[type="checkbox"]');
        const checkedTareas = Array.from(checkboxes).filter(checkbox => checkbox.checked);
        tareasMarcadas.push(...checkedTareas); // Agregar las tareas marcadas a una lista
    }

    // Si no hay tareas seleccionadas, mostrar alerta
    if (tareasMarcadas.length === 0) {
        alert('No hay tareas seleccionadas para eliminar.');
        return;
    }

    // Eliminar cada tarea seleccionada
    for (const checkbox of tareasMarcadas) {
        const contenedorPadre = checkbox.closest('.tarea-item');
        const idTarea = contenedorPadre.dataset.idTarea;

        try {
            const response = await fetch(`/api/tareas/${idTarea}`, { method: 'DELETE' });

            if (response.ok) {
                console.log(`Tarea con ID: ${idTarea} eliminada`);
                contenedorPadre.remove();
            } else {
                console.error(`Error al eliminar la tarea con ID: ${idTarea}`);
            }
        } catch (error) {
            console.error('Error de conexión:', error);
        }
    }
});

});