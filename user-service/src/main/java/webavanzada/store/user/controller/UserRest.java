package webavanzada.store.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import webavanzada.store.user.repository.entity.User;
import webavanzada.store.user.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserRest {
    @Autowired
    UserService userService;
    private BindingResult result;

    @GetMapping
    public ResponseEntity<List<User>> ListProduct(){
        List<User> users = new ArrayList<>();
        users = userService.findUserAll();
        if(users.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(users);
        }

    }



    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        log.info("Fetching User with id {}", id);
        User user = userService.getUser(id);
        if (  null == user) {
            log.error("User with id {} not found.", id);
            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user, BindingResult result) {
        this.result = result;
        log.info("Creating User : {}", user);
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        User customerDB = userService.createUser (user);
        return  ResponseEntity.status( HttpStatus.CREATED).body(customerDB);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        log.info("Updating User with id {}", id);

        User currentUser = userService.getUser(id);

        if ( null == currentUser ) {
            log.error("Unable to update. User with id {} not found.", id);
            return  ResponseEntity.notFound().build();
        }
        user.setId(id);
        currentUser=userService.updateUser(user);
        return  ResponseEntity.ok(currentUser);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
        log.info("Fetching & Deleting User with id {}", id);

        User user = userService.getUser(id);
        if ( null == user ) {
            log.error("Unable to delete. User with id {} not found.", id);
            return  ResponseEntity.notFound().build();
        }
        user = userService.deleteUser(user);
        return  ResponseEntity.ok(user);
    }


    private String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String>  error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
