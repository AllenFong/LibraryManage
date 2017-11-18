package library.springmvc.dao;

import java.util.List;

import library.springmvc.model.Borrow;

public interface IBorrowDao {

	List<Borrow> getBorrowedByReaderId(Long readerId);
	void save(Borrow borrow);
	void update(Borrow borrow);
	Borrow getBorrowedByBookCode(String bookCode);

}
