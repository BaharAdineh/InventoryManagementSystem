package com.challenge.ivms.userservice.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
public class UserInfoResponse {
    private String id;
    private String username;
    private String email;
    private List<String> roles;
}
