package com.CaseStudy.Multiworld.convert;

import org.springframework.stereotype.Component;

@Component
public interface IConvertDtoE<D, E> {
    E convertDTOtoEntity(D DTO);
}

