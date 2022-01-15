package ru.mail.dobermin.voting.service;

import java.util.List;

public interface Service<T> {

    List<T> getAll();

    T getById(String id);

    T update(String id, T t);

    List<T> saveAll(List<T> t);

    void delete(String id);
}
