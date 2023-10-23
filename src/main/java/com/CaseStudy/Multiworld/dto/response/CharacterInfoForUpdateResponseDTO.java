package com.CaseStudy.Multiworld.dto.response;

import com.CaseStudy.Multiworld.entity.Multiworld.World;
import lombok.Data;

@Data
public class CharacterInfoForUpdateResponseDTO {
    private String name;
    private int age;
    private String gender;
    private String role;
    private String ability;
    private String story;
    private String image;
    private World world;
}
