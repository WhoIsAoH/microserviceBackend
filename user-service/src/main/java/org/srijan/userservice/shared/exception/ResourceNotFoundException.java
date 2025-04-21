package org.srijan.userservice.shared.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{

        String resourceName;

        public ResourceNotFoundException(String resourceName){
            super();
            this.resourceName=resourceName;
        }


}
