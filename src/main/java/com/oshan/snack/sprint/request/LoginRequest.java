package com.oshan.snack.sprint.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
