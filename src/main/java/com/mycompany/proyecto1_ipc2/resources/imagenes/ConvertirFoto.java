/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1_ipc2.resources.imagenes;

import Excepciones.ConversionNotFound;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 *
 * @author alejandro
 */
public class ConvertirFoto {

    public byte[] convertirFoto(File foto) throws ConversionNotFound, FileNotFoundException {

        byte[] bytes = new byte[(int) foto.length()];
        try (FileInputStream file = new FileInputStream(foto)){
            file.read(bytes);
        } catch (Exception e) {
        }
        return bytes;
    }
}
