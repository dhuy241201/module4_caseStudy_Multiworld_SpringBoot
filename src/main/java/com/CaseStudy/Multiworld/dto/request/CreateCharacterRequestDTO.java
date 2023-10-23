package com.CaseStudy.Multiworld.dto.request;

import lombok.Data;

@Data
public class CreateCharacterRequestDTO {
    private String name;
    private int age;
    private String gender;
    private String role;
    private String ability;
    private String story;
    private Long worldId;
}
