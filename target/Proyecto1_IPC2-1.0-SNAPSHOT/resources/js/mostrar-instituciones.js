/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function toggleTabla() {
    const tabla = document.getElementById("tablaInstituciones");
    tabla.style.display = (tabla.style.display === "none") ? "block" : "none";
}

function mostrarOcultarInstituciones() {
    const seccion = document.getElementById("seccionInstituciones");
    seccion.classList.toggle("d-none"); 
}

function filtrarTabla() {
    const input = document.getElementById("searchInstitucion");
    const table = document.getElementById("institucionesTable").getElementsByTagName("tbody")[0];

    input.addEventListener("keyup", function () {
        const filtro = input.value.toLowerCase();
        const filas = table.getElementsByTagName("tr");

        for (let i = 0; i < filas.length; i++) {
            const celda = filas[i].getElementsByTagName("td")[0];
            if (celda) {
                const texto = celda.textContent || celda.innerText;
                filas[i].style.display = texto.toLowerCase().includes(filtro) ? "" : "none";
            }
        }
    });
}

function seleccionarInstitucion(id, nombre) {
    document.getElementById("id_institucion_hidden").value = id;
    document.getElementById("institucionSeleccionada").value = nombre;
    document.getElementById("seccionInstituciones").classList.add("d-none");
}

document.addEventListener("DOMContentLoaded", function () {
    filtrarTabla();
});


/*
 document.addEventListener('DOMContentLoaded', function () {
 toggleTabla();
 filtrarTabla();
 });
 */