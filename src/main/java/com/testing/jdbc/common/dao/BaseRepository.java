package com.testing.jdbc.common.dao;

import java.util.List;

public interface BaseRepository<E> {

    E save(E entity);

    E findById(Long id);

    List<E> find(E param, int offset, int limit);
    
    E update(E entity);

    E findRefrence(Long id);

    E delete(E entity);
}
