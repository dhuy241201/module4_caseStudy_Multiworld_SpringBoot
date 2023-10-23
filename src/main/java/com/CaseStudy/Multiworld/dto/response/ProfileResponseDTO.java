package com.CaseStudy.Multiworld.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponseDTO {
    private long id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String role;
    private String avatar;
    private List<WorldResponseDTO> worldResponseDTOList;
}
