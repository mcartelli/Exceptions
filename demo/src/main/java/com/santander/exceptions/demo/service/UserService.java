package com.santander.exceptions.demo.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.santander.exceptions.demo.dto.StatusDto;
import com.santander.exceptions.demo.dto.UserDto;
import com.santander.exceptions.demo.entity.User;
import com.santander.exceptions.demo.exceptions.NoConectionException;
import com.santander.exceptions.demo.exceptions.UserAlreadyExistException;
import com.santander.exceptions.demo.repository.UserMongoRepository;
import com.santander.exceptions.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class UserService {
    @Autowired
    ModelMapper mapper;

    UserRepository userRepository;
    UserMongoRepository userMongoRepository;
    public UserService(UserRepository userRepository, UserMongoRepository userMongoRepository){
        this.userRepository = userRepository;
        this.userMongoRepository = userMongoRepository;
    }

    private void writeOperationStatus(String message){
        try{
            FileWriter file = new FileWriter("log.txt");
            file.write(message);} catch (IOException e){
            throw new RuntimeException("No se pudo abrir el archivo");
        }
    }
    public StatusDto createUser(UserDto userDto){

        userRepository.findById(userDto.getDni()).ifPresent(user -> {
            sendEmail();
            writeOperationStatus("El usuario" + userDto.getDni() + "ya existe en la base de datos\n");
            throw new UserAlreadyExistException("El usuario ya existe en la base de datos");
        });

        //Si no catcheo la exception entonces hacemos el resto

        createUserDB(userDto);
        writeOperationStatus("Se creó el usuario " + userDto.getDni() + " con éxito\n Se mandó la notificación con éxito" +
                "\n Se hizo lo del Legacy con éxito");
        return new StatusDto("Se creó con éxito el usuario",200);

    }

    private void sendEmail(){
        //Manda email a no-response@exceptionexample.com si el usuario existe en la base de datos
    }

    // SI NO EXISTE EL USUARIO
    private void createUserDB(UserDto userDto) {
        saveUserDB(userDto);
        slackNotification();
        messageLegacy();
    }


    private void saveUserDB(UserDto userDto){
        // si el usuario no existe en la db lo guarda en una no relacional
        userMongoRepository.findById(userDto.getDni()).ifPresent(user -> {
            writeOperationStatus("El usuario" + userDto.getDni() + "ya existe en la base de datos\n");
            throw new UserAlreadyExistException("El usuario ya existe en la db no relacional");

        });
        User newUser = mapper.map(userDto,User.class);
        userMongoRepository.save(newUser);




    }

    private void slackNotification(){
        try {
            //manda notificacion al slack indicando que el usuario fue creado
        }catch (Exception e){
            writeOperationStatus("No se pudo conectar a slack correctamente\n");
            throw new NoConectionException("No se pudo conectar a slack correctamente");
        }

    }

    private void messageLegacy(){
        // hace algo
    }


}
