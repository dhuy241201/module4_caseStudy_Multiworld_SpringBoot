package com.CaseStudy.Multiworld.dto.request;

import lombok.Data;

@Data
public class UpdateCharacterRequestDTO {
    private Long id;
    private String name;
    private int age;
    private String gender;
    private String role;
    private String ability;
    private String story;
    private String image;
    private Long worldId;
}
