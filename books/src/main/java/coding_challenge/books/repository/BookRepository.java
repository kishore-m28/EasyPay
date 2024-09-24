package coding_challenge.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coding_challenge.books.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{

}
