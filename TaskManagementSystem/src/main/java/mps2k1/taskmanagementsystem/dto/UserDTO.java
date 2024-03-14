package mps2k1.taskmanagementsystem.dto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String login;
    private String password;
    private String name;
    private String lastName;
}
