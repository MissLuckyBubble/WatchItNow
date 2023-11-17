package com.example.WatchItNow.services;

import com.example.WatchItNow.dto.BaseDTO;
import com.example.WatchItNow.dto.UserDTO;
import com.example.WatchItNow.repo.UserRepo;
import com.example.WatchItNow.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService extends BaseService<User> {
    @Autowired
    UserRepo userRepo;

    @Override
    protected JpaRepository<User, Long> getRepo() {
        return userRepo;
    }

    @Override
    protected User convertDTOtoModel(BaseDTO<User> baseDTO) {
        User user = new User();
        UserDTO userDTO = (UserDTO)baseDTO;
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setCreatedAt(userDTO.getCreatedAt());
        user.setUpdatedAt(userDTO.getUpdatedAt());
        return user;
    }

    @Override
    protected void updateEntity(final User entity, BaseDTO<User> baseDTO) {
        UserDTO userDTO = (UserDTO)baseDTO;
        entity.setUsername(userDTO.getUsername());
        entity.setPassword(userDTO.getPassword());
        entity.setEmail(userDTO.getEmail());
        entity.setUpdatedAt(LocalDateTime.now());
    }

    @Override
    protected BaseDTO<User> convert(User entity) {

        return new UserDTO(entity);
    }


}


