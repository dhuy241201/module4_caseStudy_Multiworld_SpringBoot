package com.CaseStudy.Multiworld.dto.response;

import com.CaseStudy.Multiworld.entity.User.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorldForHomeResponseDTO {
    private Long id;
    private String name;
    private String fantasy;
    private Date dateCreated;
    private String image;
    private User user;
    private String dateForShow;
}
