let startDate = document.getElementById('startDate')
let endDate = document.getElementById('endDate')

startDate.addEventListener('change', (e) => {
    let startDateVal = e.target.value
    document.getElementById('startDateSelected').innerText = startDateVal
})

endDate.addEventListener('change', (e) => {
    let endDateVal = e.target.value
    document.getElementById('endDateSelected').innerText = endDateVal
})

function agregarCursos() {
    // Obtener los valores del formulario
    var nombreCurso = document.getElementById('inputnombreCurso').value;
    var profesorCurso = document.getElementById('inputprofesorCurso').value;
    var horario = document.getElementById('horario').value;

    // Verificar que todos los campos estén llenos antes de agregar el curso
    if (nombreCurso.trim() === '' || profesorCurso.trim() === '' || horario.trim() === '') {
        alert('Por favor, complete todos los campos del formulario.');
        return;
    }

    // Obtener el tbody donde se agregarán las filas de cursos
    var tbody = document.getElementById('cursosTableBody');

    // Crear una nueva fila para la tabla
    var newRow = tbody.insertRow();
    var cell1 = newRow.insertCell(0);
    var cell2 = newRow.insertCell(1);
    var cell3 = newRow.insertCell(2);
    var cell4 = newRow.insertCell(3);
    cell1.innerHTML = tbody.rows.length; // Id autoincremental
    cell2.innerHTML = horario;
    cell3.innerHTML = nombreCurso;
    cell4.innerHTML = profesorCurso;

    // Limpiar los campos del formulario
    document.getElementById('inputnombreCurso').value = '';
    document.getElementById('inputprofesorCurso').value = '';
    document.getElementById('horario').value = '';
    
    // Cerrar el modal
    var modal = new bootstrap.Modal(document.getElementById('exampleModal'));
    modal.hide();
}

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>;
