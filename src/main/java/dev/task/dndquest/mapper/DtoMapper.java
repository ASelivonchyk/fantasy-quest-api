package dev.task.dndquest.mapper;

public interface DtoMapper<E, S, Q> {
    S matToDto(E entity);
    E mapToEntity(Q dto);
}
