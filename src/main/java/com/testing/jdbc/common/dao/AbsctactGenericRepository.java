package com.testing.jdbc.common.dao;

import com.testing.jdbc.entity.EntityAware;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Hibernate;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@Component
public abstract class AbsctactGenericRepository<E extends EntityAware> implements BaseRepository<E> {

    @PersistenceContext
    protected EntityManager entityManager;

    private Class<E> type;

    {
        this.type = (Class<E>) ((ParameterizedType) getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    @Override
    public E save(E entity) {
        entityManager.persist(entity);

        return entity;
    }

    @Override
    public E findById(Long id) {
        return entityManager.find(type, id);
    }

    @Override
    public List<E> find(E entity, int offset, int limit) {
        CriteriaQuery<E> query = entityManager.getCriteriaBuilder().createQuery(type);
        query.select(query.from(type));

        return entityManager.createQuery(query)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public E update(E entity) {
        entityManager.merge(entity);
        entityManager.flush();

        return entity;
    }

    @Override
    public E findRefrence(Long id) {
        return (E) Hibernate.unproxy(entityManager.getReference(type, id));
    }

    @Override
    public E delete(E entity) {
        entityManager.remove(entity);
        entityManager.flush();
        entityManager.clear();

        return entity;
    }
}
