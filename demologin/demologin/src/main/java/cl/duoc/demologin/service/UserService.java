package cl.duoc.demologin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cl.duoc.demologin.dto.UserDto;
import cl.duoc.demologin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import cl.duoc.demologin.model.User;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //Registrarse
    public User registerUser (String username,String rawPassword){
        if(userRepository.findByUsername(username).isPresent()){
            throw new IllegalArgumentException("El usuario ya existe");
        }
        String encodedPassword = passwordEncoder.encode(rawPassword);
        User user = new User(null, username, encodedPassword);
        return userRepository.save(user);
    }
    //Login
    public boolean login(String username,String rawPassword){
        Optional<User> userOpt =userRepository.findByUsername(username);
        return userOpt.isPresent() &&
            passwordEncoder.matches(rawPassword, userOpt.get().getPassword());
    }
    //listar usuarios
    public List<UserDto>getAllUserDTO(){
        return userRepository.findAll()
            .stream()
            .map(user -> new UserDto(user.getId(), user.getUsername()))
            .toList();
    }
}
