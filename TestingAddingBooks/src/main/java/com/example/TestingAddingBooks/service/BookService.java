package com.example.TestingAddingBooks.service;

import com.example.TestingAddingBooks.entity.BookEntity;
import com.example.TestingAddingBooks.repository.BookRepository;
import java.awt.print.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookService {

  @Autowired
  private BookRepository bookRepository;  // Assuming you have a repository for CRUD operations

  // Save or update a book
  public BookEntity save(BookEntity book) {
    return bookRepository.save(book);
  }

  // Find a book by its ID
  public BookEntity findById(Long id) {
    Optional<BookEntity> bookOptional = bookRepository.findById(id);
    return bookOptional.orElse(null);  // Return null if not found
  }

  // Find all books
  public List<BookEntity> findAll() {
    return bookRepository.findAll();
  }

  // Delete a book by its ID
  public void deleteById(Long id) {
    bookRepository.deleteById(id);
  }

  // Additional service methods as needed (like searching, filtering, etc.)
}
