package com.santander.exceptions.demo.utils;

import com.santander.exceptions.demo.exceptions.NoConectionException;

import static com.santander.exceptions.demo.utils.FileUtil.writeOperationStatus;

public class SlackUtil {
    static public void slackNotification(){
        try {
            //manda notificacion al slack indicando que el usuario fue creado
        }catch (Exception e){
            writeOperationStatus("No se pudo conectar a slack correctamente\n");
            throw new NoConectionException("No se pudo conectar a slack correctamente");
        }

    }
}
