package com.CaseStudy.Multiworld.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class RegisterRequestDTO {

    private String username;

    private String password;

    private String name;

    private String email;
}
