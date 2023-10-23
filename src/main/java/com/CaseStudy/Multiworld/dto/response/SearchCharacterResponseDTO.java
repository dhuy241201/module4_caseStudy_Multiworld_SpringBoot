package com.CaseStudy.Multiworld.dto.response;

import com.CaseStudy.Multiworld.entity.Multiworld.World;
import lombok.Data;

@Data
public class SearchCharacterResponseDTO {
    private long id;
    private String name;
    private int age;
    private String gender;
    private String role;
    private String image;
    private World world;
}
