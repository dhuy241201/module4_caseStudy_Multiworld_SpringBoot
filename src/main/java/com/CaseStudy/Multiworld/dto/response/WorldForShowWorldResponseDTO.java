package com.CaseStudy.Multiworld.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorldForShowWorldResponseDTO {
    private long id;
    private String name;
    private Date dateCreated;
    private String image;
    private String dateForShow;
}
