package al.sdacademy.springdemo.util;

public interface Mapper<S, T> {
    T toDto(S entity);
    S toEntity(T dto);
}
