package com.kodilla.ecommercee.user.controller;

import com.kodilla.ecommercee.user.domain.User;
import com.kodilla.ecommercee.user.domain.UserDto;
import com.kodilla.ecommercee.user.mapper.UserMapper;
import com.kodilla.ecommercee.user.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.UnmodifiableSetException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserDbService userDbService;
    private final UserMapper userMapper;


    @GetMapping("getAllUsers")
    public List<UserDto> getAllUsers() {
        List<User> userList = userDbService.findAll();
        return userMapper.mapToUserDtoList(userList);
    }

    @GetMapping("getUser")
    public UserDto getUser(@RequestParam Long userId) throws UserNotFoundException {
        return userMapper.mapToUserDto(
                userDbService.findById(userId).orElseThrow(() -> new UserNotFoundException("User with id: " + userId + " not found")));
    }

    @PostMapping("createUser")
    public void createUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        userDbService.save(user);
    }

    @PatchMapping("blockedUser")
    public void blockUser(@RequestParam Long userId) throws UserNotFoundException {
        userDbService.blockUser(userId);
    }

    @DeleteMapping("deleteUser")
    public void deleteUser(@RequestParam Long userId) {
        userDbService.deleteById(userId);
    }

    @PutMapping("updateUser")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        User savedUser = userDbService.save(user);
        return userMapper.mapToUserDto(savedUser);
    }

    @PatchMapping("generateUserKey")
    public UserDto generateUserKey(@RequestParam Long userId) throws UserNotFoundException {
        return userMapper.mapToUserDto(userDbService.generateUserKey(userId));
    }
}
