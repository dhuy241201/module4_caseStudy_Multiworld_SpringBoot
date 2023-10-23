package com.CaseStudy.Multiworld.convert.impl;

import com.CaseStudy.Multiworld.convert.IConvertEtoD;
import com.CaseStudy.Multiworld.dto.response.UserResponseDTO;
import com.CaseStudy.Multiworld.dto.response.ViewWorldForUserResponseDTO;
import com.CaseStudy.Multiworld.entity.Multiworld.World;
import com.CaseStudy.Multiworld.entity.User.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ViewWorldConvert implements IConvertEtoD<World, ViewWorldForUserResponseDTO> {
    @Override
    public ViewWorldForUserResponseDTO convertEntityToDTO(World entity) {
        ViewWorldForUserResponseDTO viewWorldForUserResponseDTO = new ViewWorldForUserResponseDTO();
        BeanUtils.copyProperties(entity, viewWorldForUserResponseDTO);
        User userEntity = entity.getUser();
        viewWorldForUserResponseDTO.setUserDTO(
                UserResponseDTO.builder()
                        .id(userEntity.getId())
                        .username(userEntity.getUsername())
                        .name(userEntity.getName())
                        .build()
        );
        return viewWorldForUserResponseDTO;
    }
}
