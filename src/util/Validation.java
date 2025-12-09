package util;

import exception.ValidationException;

@FunctionalInterface
public interface Validation<T> {
    void validate(T value) throws ValidationException;

    // T(generic type) can be any type either String,Double,Integer etc.
}
