<%-- 
    Document   : forms
    Created on : 13 sept 2025, 20:41:42
    Author     : alejandro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    .form-container {
        max-width: 800px;
        margin: 50px auto;
        padding: 20px;
    }

    fieldset {
        border: 1px solid #ddd;
        padding: 20px;
        margin-bottom: 20px;
        border-radius: 8px;
        background-color: #f9f9f9;
    }

    legend {
        width: auto;
        padding: 0 10px;
        font-weight: bold;
        font-size: 1.1rem;
        color: #333;
    }

    input, select, textarea {
        border-radius: 5px;
        border: 1px solid #ccc;
        padding: 8px;
        width: 100%;
        box-sizing: border-box;
        margin-bottom: 15px;
    }

    input[type="file"] {
        padding: 3px;
    }

    .btn-primary {
        background-color: #0d6efd;
        border-color: #0d6efd;
    }

    .btn-secondary {
        background-color: #6c757d;
        border-color: #6c757d;
    }

    .alert-success, .alert-danger {
        margin-bottom: 20px;
        border-radius: 5px;
        padding: 10px 15px;
    }

    .card-header {
        font-size: 1.3rem;
        font-weight: bold;
    }
</style>