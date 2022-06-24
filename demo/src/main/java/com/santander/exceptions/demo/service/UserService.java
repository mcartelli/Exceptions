package com.santander.exceptions.demo.service;

import com.santander.exceptions.demo.dto.StatusDto;
import com.santander.exceptions.demo.entity.User;
import com.santander.exceptions.demo.exceptions.EmailException;
import com.santander.exceptions.demo.exceptions.NoConectionException;
import com.santander.exceptions.demo.exceptions.UserAlreadyExistException;
import com.santander.exceptions.demo.repository.UserMongoRepository;
import com.santander.exceptions.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.io.FileWriter;
import java.io.IOException;

import static com.santander.exceptions.demo.utils.EmailUtil.sendEmail;
import static com.santander.exceptions.demo.utils.FileUtil.writeOperationStatus;
import static com.santander.exceptions.demo.utils.QueueUtil.messageLegacy;
import static com.santander.exceptions.demo.utils.SlackUtil.slackNotification;

@Service
public class UserService {

    UserRepository userRepository;
    UserMongoRepository userMongoRepository;

    public UserService(UserRepository userRepository, UserMongoRepository userMongoRepository){
        this.userRepository = userRepository;
        this.userMongoRepository = userMongoRepository;
    }


    public StatusDto createUser(User user){

        userRepository.findById(user.getId()).ifPresent(usr -> {
            sendEmail();
            writeOperationStatus("El usuario" + user.getId() + "ya existe en la base de datos\n");
            throw new UserAlreadyExistException("El usuario ya existe en la base de datos");
        });

        //Si no catcheo la exception entonces hacemos el resto

        createUserDB(user);
        writeOperationStatus("Se creó el usuario " + user.getId() + " con éxito\n Se mandó la notificación con éxito" +
                "\n Se hizo lo del Legacy con éxito");
        return new StatusDto("Se creó con éxito el usuario",200);

    }


    // SI NO EXISTE EL USUARIO
    private void createUserDB(User user) {
        saveUserDB(user);
        slackNotification();
        messageLegacy();
    }




    private void saveUserDB(User user){
        // si el usuario no existe en la db lo guarda en una no relacional
        userMongoRepository.findById(user.getId()).ifPresent(usr -> {
            writeOperationStatus("El usuario" + user.getId() + "ya existe en la base de datos\n");
            throw new UserAlreadyExistException("El usuario ya existe en la db no relacional");

        });
        userMongoRepository.save(user);
    }






}
