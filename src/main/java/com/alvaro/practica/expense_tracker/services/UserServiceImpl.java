package com.alvaro.practica.expense_tracker.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alvaro.practica.expense_tracker.dtos.UserAdminDTO;
import com.alvaro.practica.expense_tracker.dtos.UserDTO;
import com.alvaro.practica.expense_tracker.exceptions.UserNotFoundException;
import com.alvaro.practica.expense_tracker.exceptions.UsernameInUseException;
import com.alvaro.practica.expense_tracker.models.user.Role;
import com.alvaro.practica.expense_tracker.models.user.User;
import com.alvaro.practica.expense_tracker.repositories.RoleRepository;
import com.alvaro.practica.expense_tracker.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public User save(UserAdminDTO userDto) {
        if(repository.existsByUsername(userDto.username())) {
            throw new UsernameInUseException("Ya existe un usuario con username: " + userDto.username());
        }
        Optional<Role> optionalRoleUser = roleRepository.findByName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        optionalRoleUser.ifPresent(roles::add);
        if(userDto.isAdmin()) {
            Optional<Role> optionalRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(roles::add);
        }
        User user = new User(userDto, roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    @Transactional
    public User update(String username, UserDTO userDto) {
        User userDb = repository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("No se ha encontrado usuario con username: " + username));
        Optional.ofNullable(userDto.username()).ifPresent(userDb::setUsername);
        Optional.ofNullable(userDto.password()).ifPresent(userDb::setPassword);
        return repository.save(userDb);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        User userDb = repository.findById(id).orElseThrow(() -> new UserNotFoundException("No se ha encontrado usuario con id: " + id));
        repository.delete(userDb);
    }    
}
