package com.CaseStudy.Multiworld.dto.response;

import com.CaseStudy.Multiworld.entity.User.User;
import lombok.Data;

@Data
public class SearchWorldResponseDTO {
    private Long id;
    private String name;
    private String image;
    private User user;
}
