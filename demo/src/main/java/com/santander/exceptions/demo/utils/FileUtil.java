package com.santander.exceptions.demo.utils;

import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
    public static void writeOperationStatus(String message){
        try{
            FileWriter file = new FileWriter("log.txt");
            file.write(message);} catch (IOException e){
            throw new RuntimeException("No se pudo abrir el archivo");
        }
    }
}
