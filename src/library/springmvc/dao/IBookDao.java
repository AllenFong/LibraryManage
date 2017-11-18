package library.springmvc.dao;

import java.util.List;

import library.springmvc.model.Book;

public interface IBookDao {
	public Long save(Book book);

	public List<Book> findBookWithWhere(String where);

	public List<Book> findAllBooks();

	public void delete(Book book);

	public Book findById(Long id);

	public void update(Book book);
}
