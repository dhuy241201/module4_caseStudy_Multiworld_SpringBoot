package com.CaseStudy.Multiworld.dto.response;

import com.CaseStudy.Multiworld.entity.Multiworld.World;
import lombok.Data;

import java.util.List;

@Data
public class UserForFindUserByIdResponseDTO {
    private String name;
    private String role;
    private String avatar;
    private List<World> worlds;
}
