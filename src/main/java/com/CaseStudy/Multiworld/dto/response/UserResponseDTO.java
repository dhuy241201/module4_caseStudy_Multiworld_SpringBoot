package com.CaseStudy.Multiworld.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDTO {
    private Long id;
    private String username;
    private String name;
}
