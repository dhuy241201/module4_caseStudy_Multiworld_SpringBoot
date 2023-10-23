package com.CaseStudy.Multiworld.convert.impl;

import com.CaseStudy.Multiworld.dto.response.WorldForHomeResponseDTO;
import com.CaseStudy.Multiworld.entity.Multiworld.World;
import com.CaseStudy.Multiworld.util.CutFantasy;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.function.Function;

@Component
public class PageWorldConvert implements Function<Page<World>,Page<WorldForHomeResponseDTO>> {

    @Override
    public Page<WorldForHomeResponseDTO> apply(Page<World> worlds) {
        return worlds.map(world -> {
            WorldForHomeResponseDTO worldForHomeResponseDto = new WorldForHomeResponseDTO();
            BeanUtils.copyProperties(world, worldForHomeResponseDto);
            Date date = worldForHomeResponseDto.getDateCreated();
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", new Locale("vi"));
            worldForHomeResponseDto.setDateForShow(df.format(date));
            worldForHomeResponseDto.setFantasy(CutFantasy.cutFantasy(worldForHomeResponseDto.getFantasy()));
            return worldForHomeResponseDto;
        });
    }
}
