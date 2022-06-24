package com.santander.exceptions.demo.utils;

import com.santander.exceptions.demo.exceptions.EmailException;

public class EmailUtil {
    public static void sendEmail(){
        try {//Manda email a no-response@exceptionexample.com si el usuario existe en la base de datos
        }catch (Exception e){
            throw new EmailException("No se pudo enviar el mail correctamente");
        }
    }
}
