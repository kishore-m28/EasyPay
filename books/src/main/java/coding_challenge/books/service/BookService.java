package coding_challenge.books.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coding_challenge.books.model.Book;
import coding_challenge.books.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	/*
	public Book addBook(Book book) {
		return bookRepository.save(book);
	}
	*/
	
	
	
}
