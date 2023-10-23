package com.CaseStudy.Multiworld.convert.impl;

import com.CaseStudy.Multiworld.convert.itf.IFindUserForOtherUsersConvert;
import com.CaseStudy.Multiworld.dto.response.UserForFindUserByIdResponseDTO;
import com.CaseStudy.Multiworld.entity.User.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class FindUserForOtherUsersConvert implements IFindUserForOtherUsersConvert {
    @Override
    public UserForFindUserByIdResponseDTO convertEntityToDTO(User entity) {
        UserForFindUserByIdResponseDTO dto = new UserForFindUserByIdResponseDTO();
        BeanUtils.copyProperties(entity,dto);
        return dto;
    }
}
