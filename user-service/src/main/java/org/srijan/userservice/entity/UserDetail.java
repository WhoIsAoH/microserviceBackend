package org.srijan.userservice.entity;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetail extends UserDetails  {

    String getRole();
    String getFirstNameForJwt();
    Integer getUserIdForJwt();


}
