package org.srijan.userservice.shared.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException (String message){
        super (message);
    }
}
