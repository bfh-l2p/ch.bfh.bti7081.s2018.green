package ch.bfh.bti7081.s2018.green.models.managers;

import java.util.List;

public interface IManager<T> {
    public T get(int id);

    public List<T> findAll();

    public T add(T item);
}
