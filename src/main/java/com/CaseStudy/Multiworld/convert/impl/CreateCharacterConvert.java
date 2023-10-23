package com.CaseStudy.Multiworld.convert.impl;

import com.CaseStudy.Multiworld.convert.IConvertDtoE;
import com.CaseStudy.Multiworld.dto.request.CreateCharacterRequestDTO;
import com.CaseStudy.Multiworld.entity.Multiworld.Character;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CreateCharacterConvert implements IConvertDtoE<CreateCharacterRequestDTO,Character> {
    @Override
    public Character convertDTOtoEntity(CreateCharacterRequestDTO DTO) {
        Character character = new Character();
        BeanUtils.copyProperties(DTO,character);
        return character;
    }
}
