package org.srijan.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.srijan.userservice.entity.Role;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {

    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private Role role;

}
