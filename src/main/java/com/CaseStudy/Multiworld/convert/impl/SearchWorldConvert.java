package com.CaseStudy.Multiworld.convert.impl;

import com.CaseStudy.Multiworld.convert.IConvertEtoD;
import com.CaseStudy.Multiworld.dto.response.SearchWorldResponseDTO;
import com.CaseStudy.Multiworld.entity.Multiworld.World;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class SearchWorldConvert implements IConvertEtoD<Page<World>,Page<SearchWorldResponseDTO>> {


    @Override
    public Page<SearchWorldResponseDTO> convertEntityToDTO(Page<World> entities) {
        return entities.map(world -> {
                    SearchWorldResponseDTO dto = new SearchWorldResponseDTO();
                    BeanUtils.copyProperties(world,dto);
                    return dto;
        });
    }
}
