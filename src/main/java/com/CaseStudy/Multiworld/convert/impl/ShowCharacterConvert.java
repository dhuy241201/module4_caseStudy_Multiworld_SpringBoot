package com.CaseStudy.Multiworld.convert.impl;

import com.CaseStudy.Multiworld.dto.response.ShowCharacterResponseDto;
import com.CaseStudy.Multiworld.entity.Multiworld.Character;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ShowCharacterConvert {
    public Page<ShowCharacterResponseDto> convertEtoD(Page<Character> characters){
        return characters.map(character -> {
            ShowCharacterResponseDto showCharacterResponseDto = new ShowCharacterResponseDto();
            BeanUtils.copyProperties(character, showCharacterResponseDto);
            if (showCharacterResponseDto.getRole().equals("")){
                showCharacterResponseDto.setRole("No Infomation");
            }
            return showCharacterResponseDto;
        });
    }
}
