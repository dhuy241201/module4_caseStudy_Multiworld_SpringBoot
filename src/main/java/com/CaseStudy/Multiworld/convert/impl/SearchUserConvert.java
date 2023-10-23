package com.CaseStudy.Multiworld.convert.impl;

import com.CaseStudy.Multiworld.convert.IConvertEtoD;
import com.CaseStudy.Multiworld.dto.response.SearchUserResponseDTO;
import com.CaseStudy.Multiworld.entity.User.User;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class SearchUserConvert implements IConvertEtoD<Page<User>,Page<SearchUserResponseDTO>> {
    @Override
    public Page<SearchUserResponseDTO> convertEntityToDTO(Page<User> entities) {
        return entities.map(user -> {
            SearchUserResponseDTO dto = new SearchUserResponseDTO();
            BeanUtils.copyProperties(user,dto);
            return dto;
        });
    }
}
