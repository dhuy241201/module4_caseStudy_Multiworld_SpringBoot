package com.CaseStudy.Multiworld.convert.impl;

import com.CaseStudy.Multiworld.convert.IConvertEtoD;
import com.CaseStudy.Multiworld.dto.response.ViewCharacterResponseDTO;
import com.CaseStudy.Multiworld.entity.Multiworld.Character;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ViewCharacterConvert implements IConvertEtoD<Character, ViewCharacterResponseDTO> {
    @Override
    public ViewCharacterResponseDTO convertEntityToDTO(Character entity) {
        ViewCharacterResponseDTO viewCharacterResponseDTO = new ViewCharacterResponseDTO();
        BeanUtils.copyProperties(entity,viewCharacterResponseDTO);
        return viewCharacterResponseDTO;
    }
}
