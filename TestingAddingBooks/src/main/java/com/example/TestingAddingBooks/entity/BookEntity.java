package com.example.TestingAddingBooks.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class BookEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "book_entity")
  private Long id;
  private String title;
  private String author;
  private String isbn;
  private BigDecimal price;
  private String category;
  private String description;

  public Long getId() {
    return id;
  }
//private String image; // Store image path or URL if needed

  @Lob // Annotate as a large object
  @Column(name = "image", columnDefinition = "oid")
  private byte[] image; // Store the image as a byte array


}
