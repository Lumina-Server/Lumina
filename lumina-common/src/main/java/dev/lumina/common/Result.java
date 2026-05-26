// lumina-common/src/main/java/dev/lumina/common/Result.java
package dev.lumina.common;

import java.util.Objects;
import java.util.function.Function;

public final class Result<T> {
    private final T value;
    private final Throwable error;

    private Result(T value, Throwable error) {
        this.value = value;
        this.error = error;
    }

    public static <T> Result<T> success(T value) {
        return new Result<>(value, null);
    }

    public static <T> Result<T> failure(Throwable error) {
        return new Result<>(null, Objects.requireNonNull(error, "error"));
    }

    public boolean isSuccess() {
        return error == null;
    }

    public boolean isFailure() {
        return error != null;
    }

    public T value() {
        if (error != null) {
            throw new IllegalStateException("Result is failure", error);
        }
        return value;
    }

    public Throwable error() {
        return error;
    }

    public <U> Result<U> map(Function<? super T, ? extends U> mapper) {
        Objects.requireNonNull(mapper, "mapper");
        if (isFailure()) {
            return failure(error);
        }
        try {
            return success(mapper.apply(value));
        } catch (Throwable t) {
            return failure(t);
        }
    }
}
