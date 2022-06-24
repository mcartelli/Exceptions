package com.santander.exceptions.demo.utils;

import com.santander.exceptions.demo.exceptions.NoConectionException;

import static com.santander.exceptions.demo.utils.FileUtil.writeOperationStatus;

public class QueueUtil {
    static public void messageLegacy(){
        try{
            // manda mensaje a la cola
        }catch (Exception e){
            writeOperationStatus("No se pudo conectar a la cola\n");
            throw new NoConectionException("No se pudo conectar a la cola");
        }
    }
}
