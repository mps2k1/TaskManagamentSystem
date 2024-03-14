package mps2k1.taskmanagementsystem.controller;
import lombok.RequiredArgsConstructor;
import mps2k1.taskmanagementsystem.dto.LoginRequest;
import mps2k1.taskmanagementsystem.dto.RegistrationRequest;
import mps2k1.taskmanagementsystem.dto.UserDTO;
import mps2k1.taskmanagementsystem.security.CustomUserDetailsService;
import mps2k1.taskmanagementsystem.security.JwtTokenUtils;
import mps2k1.taskmanagementsystem.service.imp.UserServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtTokenUtils jwtTokenUtils;
    private final UserServiceImp userService;
@PostMapping("/login")
    public ResponseEntity<?> createAuthToken(LoginRequest loginRequest, AuthenticationManager authenticationManager) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequest.getLogin());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(token);
    }
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationRequest registrationRequest) {
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin(registrationRequest.getLogin());
        userDTO.setPassword(registrationRequest.getPassword());
        userService.createUser(userDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
