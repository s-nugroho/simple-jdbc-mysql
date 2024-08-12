package com.testing.jdbc.module.book.repository;

import com.testing.jdbc.common.dao.AbsctactGenericRepository;
import com.testing.jdbc.common.dao.BaseRepository;
import com.testing.jdbc.entity.Book;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository extends AbsctactGenericRepository<Book> implements BaseRepository<Book> {

}
