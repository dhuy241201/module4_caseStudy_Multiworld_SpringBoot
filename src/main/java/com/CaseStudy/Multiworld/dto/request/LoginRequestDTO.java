package com.CaseStudy.Multiworld.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequestDTO {
    String username;
    String password;
}
