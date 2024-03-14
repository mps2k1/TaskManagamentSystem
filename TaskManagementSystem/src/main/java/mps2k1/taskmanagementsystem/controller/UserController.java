package mps2k1.taskmanagementsystem.controller;
import lombok.RequiredArgsConstructor;
import mps2k1.taskmanagementsystem.dto.UserDTO;
import mps2k1.taskmanagementsystem.exception.UserNotFoundException;
import mps2k1.taskmanagementsystem.service.imp.UserServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServiceImp userServiceImp;
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        try {
            UserDTO userDTO =userServiceImp.getUserById(id);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }
        catch (UserNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id)
    {
        try {
            userServiceImp.deleteAccount(id);
            return new ResponseEntity<>("Delete success",HttpStatus.NO_CONTENT);
        }
        catch (UserNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
