package br.com.dbserver.voting.converters;

public interface Mapper<T, U> {
    U map(T t, U u);
}
