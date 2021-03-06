package com.quizapp.service.interf;

public interface ConverterDto<E, D> {
    E fromDtoToEntity(D dto);

    D fromEntityToDto(E entity);
}
