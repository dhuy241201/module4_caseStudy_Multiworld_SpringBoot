package com.CaseStudy.Multiworld.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateWorldRequestDTO {
    private String name;
    private String fantasy;
    private String image;
    private String username;
}
