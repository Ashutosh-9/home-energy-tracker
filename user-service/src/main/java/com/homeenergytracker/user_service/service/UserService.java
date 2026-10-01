package com.homeenergytracker.user_service.service;

import com.homeenergytracker.user_service.dto.UserDto;
import com.homeenergytracker.user_service.entity.User;
import com.homeenergytracker.user_service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

   private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserDto createUser(UserDto input) {
        final User createdUser= User.builder()
                .name(input.getName())
                .surname(input.getSurname())
                .email(input.getEmail())
                .address(input.getAddress())
                .alerting(input.isAlerting())
                .energyAlertingThreshold(input.getEnergyAlertingThreshold())
                .build();
        final User saved=userRepository.save(createdUser);
        return toDto(saved);

        }

    public UserDto getUserById(Long id){
        return userRepository.findById(id).map(this::toDto).orElse(null);
    }

    public void updateUser(Long id, UserDto input){
        User user=userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("User Not Found"));
        user.setName(input.getName());
        user.setSurname(input.getSurname());
        user.setEmail(input.getEmail());
        user.setAddress(input.getAddress());
        user.setAlerting(input.isAlerting());
        user.setEnergyAlertingThreshold(input.getEnergyAlertingThreshold());
        userRepository.save(user);

    }

    public void deleteUser(Long id){
        User user=userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("User Not Found"));
        userRepository.delete(user);
    }


    private UserDto toDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .address(user.getAddress())
                .alerting(user.isAlerting())
                .energyAlertingThreshold(user.getEnergyAlertingThreshold())
                .build();
    }



}



