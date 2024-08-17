package com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.services;

import com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.dto.SignUpDTO;
import com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.dto.UserDTO;
import com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.entities.UserEntity;
import com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.exceptions.ResourceNotFoundException;
import com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {


    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User with email "+ username + " not found"));
    }


    public UserDetails getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with email "+ id + " not found"));
    }

    public UserDetails getUserByEmail(String email){
        return userRepository.findByEmail(email).orElse(null);
    }


    public UserDTO signUp(SignUpDTO signUpDTO) {
     Optional<UserEntity> user =  userRepository.findByEmail(signUpDTO.getEmail());
      if(user.isPresent()){
          throw  new BadCredentialsException("User Already present this email " + signUpDTO.getEmail());
      }
      UserEntity newUser = modelMapper.map(signUpDTO , UserEntity.class);
      newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
     UserEntity saveUser = userRepository.save(newUser);
      return  modelMapper.map(saveUser, UserDTO.class);
    }

    public UserEntity saveNewUser(UserEntity newUser) {
        return userRepository.save(newUser);
    }
}
