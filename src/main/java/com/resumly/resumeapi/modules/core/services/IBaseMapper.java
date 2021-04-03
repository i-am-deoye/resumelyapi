package com.resumly.resumeapi.modules.core.services;

import java.util.List;

public interface IBaseMapper<D,E> {
    D toDTO(E entity);
    E toEntity(D dto);
    List<D> toDTOs(List<E> entities);
    List<E> toEntities(List<D> dtos);
}
