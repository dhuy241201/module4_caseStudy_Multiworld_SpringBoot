package com.CaseStudy.Multiworld.dto.response;


import lombok.Data;

@Data
public class ViewWorldForUserResponseDTO {
    private long id;
    private String name;
    private String image;
    private String fantasy;
    private UserResponseDTO userDTO;
}
