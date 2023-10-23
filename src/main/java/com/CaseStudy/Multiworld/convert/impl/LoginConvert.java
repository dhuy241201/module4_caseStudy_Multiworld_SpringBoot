package com.CaseStudy.Multiworld.convert.impl;

import com.CaseStudy.Multiworld.convert.IConvertDtoE;
import com.CaseStudy.Multiworld.convert.IConvertEtoD;
import com.CaseStudy.Multiworld.dto.request.LoginRequestDTO;
import com.CaseStudy.Multiworld.dto.response.LoginResponseDTO;
import com.CaseStudy.Multiworld.entity.User.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class LoginConvert implements IConvertDtoE<LoginRequestDTO, User>, IConvertEtoD<User, LoginResponseDTO> {

    @Override
    public User convertDTOtoEntity(LoginRequestDTO DTO) {
        User user = new User();
        BeanUtils.copyProperties(DTO,user);
        return user;
    }

    @Override
    public LoginResponseDTO convertEntityToDTO(User entity) {
        LoginResponseDTO loginResponseDto = new LoginResponseDTO();
        BeanUtils.copyProperties(entity,loginResponseDto);
        return loginResponseDto;
    }
}
