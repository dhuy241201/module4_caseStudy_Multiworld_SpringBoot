package com.CaseStudy.Multiworld.convert.impl;

import com.CaseStudy.Multiworld.convert.IConvertEtoD;
import com.CaseStudy.Multiworld.dto.response.CharacterInfoForUpdateResponseDTO;
import com.CaseStudy.Multiworld.entity.Multiworld.Character;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class GetCharacterInfoForUpdateConvert implements IConvertEtoD<Character, CharacterInfoForUpdateResponseDTO> {
    @Override
    public CharacterInfoForUpdateResponseDTO convertEntityToDTO(Character entity) {
        CharacterInfoForUpdateResponseDTO DTO = new CharacterInfoForUpdateResponseDTO();
        BeanUtils.copyProperties(entity,DTO);
        return DTO;
    }
}
