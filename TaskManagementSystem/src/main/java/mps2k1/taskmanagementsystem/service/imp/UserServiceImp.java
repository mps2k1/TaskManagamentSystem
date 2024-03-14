package mps2k1.taskmanagementsystem.service.imp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mps2k1.taskmanagementsystem.dto.UserDTO;
import mps2k1.taskmanagementsystem.entity.UserEntity;
import mps2k1.taskmanagementsystem.exception.UserNotFoundException;
import mps2k1.taskmanagementsystem.mapper.UserMapper;
import mps2k1.taskmanagementsystem.repository.UserRepository;
import mps2k1.taskmanagementsystem.service.UserService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public UserDTO getUserById(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isPresent()) {
            UserEntity userEntity = user.get();
            return userMapper.map(userEntity);

        } else throw new UserNotFoundException("User with id: " + id + " " + "not found");
    }
    @Override
    public void deleteAccount(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isPresent()){
            UserEntity userEntity = user.get();
            userRepository.delete(userEntity);
        }
        else throw new UserNotFoundException("User with id: "+id+" "+"not found");
        log.warn("Attempting to delete account with non-existing user ID:"+" "+id);
    }
    public UserDTO createUser(UserDTO userDTO){
        UserEntity user = userMapper.mapToDao(userDTO);
        UserEntity savedUser = userRepository.save(user);
        return userDTO;
    }
    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::map)
                .collect(Collectors.toList());
    }
}
