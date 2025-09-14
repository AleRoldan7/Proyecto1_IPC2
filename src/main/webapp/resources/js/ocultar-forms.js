/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function validarPrimerFieldset() {
    const primerFieldset = document.querySelector('fieldset:first-of-type');
    const cuentaFieldset = document.getElementById('cuentaFieldset');
    const inputsPrimerFieldset = primerFieldset.querySelectorAll('input[required]');

    let todosValidos = true;

    inputsPrimerFieldset.forEach(input => {
        if (!input.checkValidity()) {
            todosValidos = false;
        }
    });

    cuentaFieldset.style.display = todosValidos ? 'block' : 'none';
}

function inicializarValidacionUsuario() {
    const inputsPrimerFieldset = document.querySelectorAll('fieldset:first-of-type input[required]');
    inputsPrimerFieldset.forEach(input => {
        input.addEventListener('input', validarPrimerFieldset);
    });
}

document.addEventListener('DOMContentLoaded', inicializarValidacionUsuario);
