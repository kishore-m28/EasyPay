package coding_challenge.books.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import coding_challenge.books.model.Book;
import coding_challenge.books.service.BookService;


@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;
	/*
	@PostMapping("/add")
	public Book addEmployee(@RequestBody Book book){
		return bookService.addBook(book);
	}*/
}
