package mps2k1.taskmanagementsystem.service;


import mps2k1.taskmanagementsystem.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO getUserById(Long id);
    void deleteAccount(Long id);
    List<UserDTO> getAllUsers();


}
