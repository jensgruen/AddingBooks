package com.example.TestingAddingBooks.controller;

import com.example.TestingAddingBooks.entity.BookEntity;
import com.example.TestingAddingBooks.repository.BookRepository;
import com.example.TestingAddingBooks.service.BookService;
import java.awt.print.Book;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//@Controller
//@RequestMapping("/books")
//public class BookController {
//
//  private final BookRepository bookRepository;
//
//  public BookController(BookRepository bookRepository) {
//    this.bookRepository = bookRepository;
//  }
//
//  @GetMapping
//  public String showBooks(Model model) {
//    List<BookEntity> books = bookRepository.findAll();
//    model.addAttribute("books", books);
//    return "books";
//  }
//
//  @PostMapping("/add")
//  public String addBook(BookEntity book, MultipartFile image) {
////    if (!image.isEmpty()) {
////      // Code to save the image to the database or a file system
////      // Then set the image URL or path to book.setImage(imagePath);
////    }
////    try {
////      if (!image.isEmpty()) {
////        book.setImage(image.getBytes());
////      }
////    } catch (IOException e) {
////      e.printStackTrace();
////    }
//    bookRepository.save(book);
//    return "redirect:/books";
//  }
//}

@Controller
@RequestMapping("/books")
public class BookController {

  @Autowired
  private BookRepository bookRepository;

  @GetMapping
  public String getBooks(Model model) {
    model.addAttribute("books", bookRepository.findAll());
    return "books"; // This matches the Thymeleaf template books.html
  }

  @PostMapping("/add")
  public String addBook(
      @RequestParam("title") String title,
      @RequestParam("author") String author,
      @RequestParam("category") String category,
      @RequestParam("isbn") String isbn,
      @RequestParam("price") double price,
      @RequestParam("description") String description,
      @RequestParam("image") MultipartFile imageFile,
      RedirectAttributes redirectAttributes) {

    BookEntity book = new BookEntity();
    book.setTitle(title);
    book.setAuthor(author);
    book.setCategory(category);
    book.setIsbn(isbn);
    book.setPrice(BigDecimal.valueOf(price));
    book.setDescription(description);

    try {
      if (!imageFile.isEmpty()) {
        book.setImage(imageFile.getBytes());
      }
    } catch (IOException e) {
      e.printStackTrace();
      redirectAttributes.addFlashAttribute("error", "Image upload failed!");
      return "redirect:/books";
    }

    bookRepository.save(book);
    return "redirect:/books";
  }


  @PostMapping("/edit")
  public String editBook(
      @RequestParam("title") String title,
      @RequestParam("author") String author,
      @RequestParam("category") String category,
      @RequestParam("isbn") String isbn,
      @RequestParam("price") double price,
      @RequestParam("description") String description,
      @RequestParam("image") MultipartFile imageFile,
      RedirectAttributes redirectAttributes) {

    BookEntity book = new BookEntity();
    book.setTitle(title);
    book.setAuthor(author);
    book.setCategory(category);
    book.setIsbn(isbn);
    book.setPrice(BigDecimal.valueOf(price));
    book.setDescription(description);

    try {
      if (!imageFile.isEmpty()) {
        book.setImage(imageFile.getBytes());
      }
    } catch (IOException e) {
      e.printStackTrace();
      redirectAttributes.addFlashAttribute("error", "Image upload failed!");
      return "redirect:/books";
    }

    bookRepository.save(book);
    return "redirect:/books";
  }

  @PostMapping("/edit/{id}")
  public String editBook(
      @PathVariable("id") Long id,
      @RequestParam("title") String title,
      @RequestParam("author") String author,
      @RequestParam("category") String category,
      @RequestParam("isbn") String isbn,
      @RequestParam("price") double price,
      @RequestParam("description") String description,
      @RequestParam("image") MultipartFile imageFile,
      RedirectAttributes redirectAttributes) {
    System.out.println(("book id: " + id));

    BookEntity book = bookRepository.findById(id).orElse(null);
    if (book == null) {
      redirectAttributes.addFlashAttribute("error", "Book not found!");
      return "redirect:/books";
    }

    book.setTitle(title);
    book.setAuthor(author);
    book.setCategory(category);
    book.setIsbn(isbn);
    book.setPrice(BigDecimal.valueOf(price));
    book.setDescription(description);

    try {
      if (!imageFile.isEmpty()) {
        book.setImage(imageFile.getBytes());
      }
    } catch (IOException e) {
      e.printStackTrace();
      redirectAttributes.addFlashAttribute("error", "Image upload failed!");
      return "redirect:/books";
    }

    bookRepository.save(book);
    return "redirect:/books";
  }






  @GetMapping("/image/{id}")
  @ResponseBody
  public byte[] getImage(@PathVariable("id") Long id) {
    BookEntity book = bookRepository.findById(id).orElse(null);
    return (book != null) ? book.getImage() : null;
  }
}

//@Controller
//@RequestMapping("/books")
//public class BookController {
//
//  @Autowired
//  private BookService bookService;  // Assuming you have a service layer to handle book logic.
//
//  // Add a new book (display the form and handle form submission)
////  @GetMapping("/add")
////  public String showAddBookForm(Model model) {
////    model.addAttribute("book", new Book());  // Assuming you have a Book model class
////    return "books";  // This is the Thymeleaf template for adding a book
////  }
//
//    @GetMapping
//  public String showBooks(Model model) {
//    List<BookEntity> books = bookService.findAll(); //bookRepository.findAll();
//    model.addAttribute("books", books);
//    return "books";
//  }
//
//  @PostMapping("/add")
//  public String addBook(@ModelAttribute("book") BookEntity book,
//      @RequestParam("image") MultipartFile image) throws IOException {
//    if (!image.isEmpty()) {
//      // Save image to a location and set the image URL to the book object
//      String imageUrl = saveImage(image);
//      book.setImage(imageUrl.getBytes());
//    }
//    bookService.save(book);  // Save the book to the database
//    return "redirect:/books";  // Redirect to the list of books after saving
//  }
//
//  // Edit an existing book (show form and handle form submission)
//  @GetMapping("/edit/{id}")
//  public String showEditBookForm(@PathVariable("id") Long id, Model model) {
//    BookEntity book = bookService.findById(id);  // Get the book by its ID
//    model.addAttribute("book", book);  // Pass the book to the view for editing
//    return "edit-book";  // Thymeleaf template for editing a book
//  }
//
//  @PostMapping("/edit/{id}")
//  public String updateBook(@PathVariable("id") Long id,
//      @ModelAttribute("book") BookEntity book,
//      @RequestParam("image") MultipartFile image) throws IOException {
//    BookEntity existingBook = bookService.findById(id);  // Find the existing book
//    if (existingBook != null) {
//      // Update the book details
//      existingBook.setTitle(book.getTitle());
//      existingBook.setAuthor(book.getAuthor());
//      existingBook.setCategory(book.getCategory());
//      existingBook.setDescription(book.getDescription());
//      existingBook.setIsbn(book.getIsbn());
//      existingBook.setPrice(book.getPrice());
//
//      if (!image.isEmpty()) {
//        // Save new image if uploaded
//        String imageUrl = saveImage(image);
//        existingBook.setImage(imageUrl.getBytes());
//      }
//
//      bookService.save(existingBook);  // Save the updated book
//    }
//    return "redirect:/books";  // Redirect to the list of books after editing
//  }
//
//  // Helper method to save the image and return the image URL
//  private String saveImage(MultipartFile image) throws IOException {
//    // Implement image saving logic (e.g., save to disk, cloud, or database)
//    // For example, you can save it to a local directory and return the URL path.
//    // Here we assume the image is saved and we return a simple string as the URL.
//    String imageUrl = "/images/" + image.getOriginalFilename();  // Simplified for this example
//    // Save image logic (e.g., FileUtils.save(image, imageUrl));
//    return imageUrl;
//  }
//}