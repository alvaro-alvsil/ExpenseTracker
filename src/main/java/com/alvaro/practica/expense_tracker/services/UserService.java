package com.alvaro.practica.expense_tracker.services;

import java.util.List;

import com.alvaro.practica.expense_tracker.dtos.UserAdminDTO;
import com.alvaro.practica.expense_tracker.dtos.UserDTO;
import com.alvaro.practica.expense_tracker.models.user.User;

public interface UserService {

    List<User> findAll();

    User save(UserAdminDTO userDto);

    User update(String username, UserDTO userDto);

    void delete(Long id);
}
