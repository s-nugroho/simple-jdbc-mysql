package com.testing.jdbc.module.book.repository;

import com.testing.jdbc.common.dao.AbsctactGenericRepository;
import com.testing.jdbc.common.dao.BaseRepository;
import com.testing.jdbc.entity.Author;
import com.testing.jdbc.entity.Book;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository extends AbsctactGenericRepository<Book> implements BaseRepository<Book> {

    @Override
    public List<Book> find(Book param, int offset, int limit) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Book> query = builder.createQuery(Book.class);

        Root<Book> root = query.from(Book.class);
        root.fetch("author", JoinType.INNER);

        Join<Book, Author> authorJoin = root.join("author", JoinType.INNER);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get("id"), param.getId()));
        predicates.add(builder.equal(authorJoin.get("name"),
                param.getAuthors()
                        .stream()
                        .findFirst().get()
                        .getAuthor()
                        .getName()
        ));

        query.where(predicates.toArray(new Predicate[0]));

        try {
            return entityManager
                    .createQuery(query)
                    .getResultList();
        } catch (NoResultException ignored) {}

        return null;
    }
}
