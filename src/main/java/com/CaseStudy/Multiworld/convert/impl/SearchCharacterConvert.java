package com.CaseStudy.Multiworld.convert.impl;

import com.CaseStudy.Multiworld.convert.IConvertEtoD;
import com.CaseStudy.Multiworld.dto.response.SearchCharacterResponseDTO;
import com.CaseStudy.Multiworld.entity.Multiworld.Character;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class SearchCharacterConvert implements IConvertEtoD<Page<Character>, Page<SearchCharacterResponseDTO>> {

    @Override
    public Page<SearchCharacterResponseDTO> convertEntityToDTO(Page<Character> entities) {
        return entities.map(entity -> {
            SearchCharacterResponseDTO dto = new SearchCharacterResponseDTO();
            BeanUtils.copyProperties(entity, dto);
            return dto;
        });
    }
}
