package library.springmvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import library.springmvc.model.Book;
@Repository("bookDao")
public class BookDaoImpl implements IBookDao {

	@Autowired
	private SessionFactory sessionFactory;
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	@Override
	public Long save(Book book) {
		return (Long) getSession().save(book);
	}
	@Override
	public List<Book> findBookWithWhere(String where) {
		return getSession().createQuery("from Book as b where " + where).getResultList();
	}
	@Override
	public List<Book> findAllBooks() {
		return findBookWithWhere("1=1");
	}
	@Override
	public void delete(Book book) {
		getSession().delete(book);
		
	}
	@Override
	public Book findById(Long id) {
		return getSession().get(Book.class, id);
	}
	@Override
	public void update(Book book) {
		getSession().merge(book);
	}

}
