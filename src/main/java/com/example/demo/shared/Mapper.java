package com.example.demo.shared;

public interface Mapper<T, K, E extends Object> {
    public T toDto(E entity);
    public K toExtendedDto(E entity);
    public E toEntity(K extendedDto);
}
