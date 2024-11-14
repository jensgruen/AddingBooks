package com.example.TestingAddingBooks.repository;

import com.example.TestingAddingBooks.entity.BookEntity;
import java.awt.print.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
}