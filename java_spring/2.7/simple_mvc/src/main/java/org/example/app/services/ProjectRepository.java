package org.example.app.services;

import java.util.List;

public interface ProjectRepository<T> {

    public List<T> retrieveAll();

    public void store(T book);

    boolean removeItemById(Integer bookIdToRemove);

    void removeBooksByRegex(String queryRegex);
}
