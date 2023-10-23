package com.CaseStudy.Multiworld.convert.impl;

import com.CaseStudy.Multiworld.convert.IConvertDtoE;
import com.CaseStudy.Multiworld.dto.request.UpdateUserRequestDTO;
import com.CaseStudy.Multiworld.entity.User.User;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserConvert implements IConvertDtoE<UpdateUserRequestDTO, User> {
    @Override
    public User convertDTOtoEntity(UpdateUserRequestDTO DTO) {
        User user = new User();
        user.setId(DTO.getId());
        user.setUsername(DTO.getUsername());
        user.setPassword(DTO.getNewPassword());
        user.setName(DTO.getNewName());
        user.setEmail(DTO.getNewEmail());
        user.setAvatar(DTO.getNewAvatar());
        return user;
    }
}
