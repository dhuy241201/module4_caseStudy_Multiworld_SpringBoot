package com.CaseStudy.Multiworld.convert.impl;

import com.CaseStudy.Multiworld.convert.IConvertEtoD;
import com.CaseStudy.Multiworld.dto.response.ShowWorldForOtherUserResponseDTO;
import com.CaseStudy.Multiworld.entity.Multiworld.World;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShowWorldForOtherUserConvert implements IConvertEtoD<List<World>,List<ShowWorldForOtherUserResponseDTO>> {
    @Override
    public List<ShowWorldForOtherUserResponseDTO> convertEntityToDTO(List<World> entities) {
        List<ShowWorldForOtherUserResponseDTO> dtoList = new ArrayList<>();
        for (World world : entities){
            ShowWorldForOtherUserResponseDTO dto = new ShowWorldForOtherUserResponseDTO();
            BeanUtils.copyProperties(world,dto);
            dtoList.add(dto);
        }
        return dtoList;
    }
}
