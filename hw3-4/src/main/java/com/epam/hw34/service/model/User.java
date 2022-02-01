package com.epam.hw34.service.model;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class User {

    private String firstName;
    private String lastName;
    private String email;
    private Instant writtenOn;
    private String password;
    private String repeatPassword;

}
