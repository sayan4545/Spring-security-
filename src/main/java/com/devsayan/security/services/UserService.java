package com.devsayan.security.services;

import com.devsayan.security.dtos.LoginDTO;
import com.devsayan.security.dtos.SignUpDTO;
import com.devsayan.security.dtos.UserDTO;
import com.devsayan.security.entities.User;
import com.devsayan.security.exceptions.ResourceNotFoundException;
import com.devsayan.security.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service


public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(()-> new BadCredentialsException("No user by this email: "+ username));
    }
    public User getUserFromUserId(Long userId){
        User user =userRepository.findById(userId).orElseThrow(()-> new BadCredentialsException("No user found with id: "+ userId));
        return user;
    }

    public UserDTO signUp(SignUpDTO signUpDTO){
        Optional<User> user = userRepository.findByEmail(signUpDTO.getEmail());
        if(user.isPresent()) throw new BadCredentialsException("User already present");
        User toBeCreatedUser = modelMapper.map(signUpDTO,User.class);
        toBeCreatedUser.setPassword(passwordEncoder.encode(toBeCreatedUser.getPassword()));
        User savedUser = userRepository.save(toBeCreatedUser);
        return modelMapper.map(savedUser,UserDTO.class);
    }





}
