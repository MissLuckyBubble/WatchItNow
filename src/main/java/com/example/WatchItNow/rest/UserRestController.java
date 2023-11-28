package com.example.WatchItNow.rest;

import com.example.WatchItNow.dto.BaseDTO;
import com.example.WatchItNow.dto.UserDTO;
import com.example.WatchItNow.models.User;
import com.example.WatchItNow.services.UserService;
import jakarta.persistence.Id;
import jdk.jfr.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserRestController {

    private UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    private UserRestController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<UserDTO> list(){
        List<User> users = userService.findAll();
        return users
                .stream()
                .map(this::convetToUserDto)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{userId}")
    public UserDTO getUser(@PathVariable(name = "userId") long userId){
        Optional<User> optionalUser = userService.getEntity(userId);
        return optionalUser.map(this::convetToUserDto).orElse(null);
    }

    private UserDTO convetToUserDto(User user){
        final UserDTO result = modelMapper.map(user, UserDTO.class);
        return result;
    }
    private User convertUserDTOtoModel(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        return user;
    }

    @PostMapping()
    public BaseDTO<User> create(@RequestBody UserDTO newUser){
        User user = convertUserDTOtoModel(newUser);
        return convetToUserDto(userService.create(user));
    }

    @PutMapping ()
    public BaseDTO<User> update(@RequestBody UserDTO newUser){
        User user = convertUserDTOtoModel(newUser);
        return convetToUserDto(userService.update(user));
    }

    @DeleteMapping("/{userID}")
    public ResponseEntity<String> delete(@PathVariable long userId){
        boolean isRemoved = userService.remove(userId);
        String deletedMessage = "User with id: " + userId + " was deleted";
        String notDeletedMessage = "User with id: " + userId + " does not exist";
        return isRemoved? new ResponseEntity(deletedMessage, HttpStatusCode.valueOf(200)) :
                new ResponseEntity<>(notDeletedMessage,HttpStatusCode.valueOf(404));
    }
}
