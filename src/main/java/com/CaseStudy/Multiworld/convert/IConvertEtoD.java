package com.CaseStudy.Multiworld.convert;

public interface IConvertEtoD<E, D> {
    D convertEntityToDTO(E entity);
}
