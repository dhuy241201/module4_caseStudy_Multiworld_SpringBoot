package com.CaseStudy.Multiworld.convert.impl;

import com.CaseStudy.Multiworld.dto.response.WorldForShowWorldResponseDTO;
import com.CaseStudy.Multiworld.entity.Multiworld.World;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Component
public class ShowWorldConvert {
    public Page<WorldForShowWorldResponseDTO> convertEtoD(Page<World> worlds){
        return worlds.map(world -> {
            WorldForShowWorldResponseDTO worldForHomeResponseDto = new WorldForShowWorldResponseDTO();
            BeanUtils.copyProperties(world, worldForHomeResponseDto);
            Date date = worldForHomeResponseDto.getDateCreated();
            DateFormat df = new SimpleDateFormat("EEEEEEEEEEE, dd-MM-yyyy HH:mm:ss", new Locale("vi"));
            worldForHomeResponseDto.setDateForShow(df.format(date));
            return worldForHomeResponseDto;
        });
    }
}
