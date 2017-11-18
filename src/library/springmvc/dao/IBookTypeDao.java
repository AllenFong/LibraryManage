package library.springmvc.dao;

import java.util.List;

import library.springmvc.model.BookType;

public interface IBookTypeDao {
	public List<BookType> findAll();
}
