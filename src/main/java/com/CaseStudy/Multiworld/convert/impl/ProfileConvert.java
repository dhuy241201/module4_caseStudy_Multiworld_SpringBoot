package com.CaseStudy.Multiworld.convert.impl;

import com.CaseStudy.Multiworld.convert.IConvertDtoE;
import com.CaseStudy.Multiworld.convert.IConvertEtoD;
import com.CaseStudy.Multiworld.dto.request.ProfileRequestDTO;
import com.CaseStudy.Multiworld.dto.response.ProfileResponseDTO;
import com.CaseStudy.Multiworld.entity.User.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProfileConvert implements IConvertDtoE<ProfileRequestDTO, User>, IConvertEtoD<User, ProfileResponseDTO> {

    @Override
    public User convertDTOtoEntity(ProfileRequestDTO DTO) {
        User user = new User();
        BeanUtils.copyProperties(DTO,user);
        return user;
    }

    @Override
    public ProfileResponseDTO convertEntityToDTO(User entity) {
        ProfileResponseDTO profileResponseDto = new ProfileResponseDTO();
        BeanUtils.copyProperties(entity,profileResponseDto);
        return profileResponseDto;
    }
}
