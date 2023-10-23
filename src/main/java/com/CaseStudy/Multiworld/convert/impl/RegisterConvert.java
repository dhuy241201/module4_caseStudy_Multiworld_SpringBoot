package com.CaseStudy.Multiworld.convert.impl;

import com.CaseStudy.Multiworld.convert.IConvertDtoE;
import com.CaseStudy.Multiworld.dto.request.RegisterRequestDTO;
import com.CaseStudy.Multiworld.entity.User.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class RegisterConvert implements IConvertDtoE<RegisterRequestDTO, User> {
    @Override
    public User convertDTOtoEntity(RegisterRequestDTO DTO) {
        User user = new User();
        BeanUtils.copyProperties(DTO,user);
        return user;
    }
}
