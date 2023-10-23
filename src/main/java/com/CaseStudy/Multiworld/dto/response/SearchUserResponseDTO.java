package com.CaseStudy.Multiworld.dto.response;

import lombok.Data;

@Data
public class SearchUserResponseDTO {
    private Long id;
    private String username;
    private String name;
    private String avatar;
    private String role;
}
