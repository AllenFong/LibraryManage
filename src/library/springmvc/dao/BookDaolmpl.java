package library.springmvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import library.springmvc.model.Book;

@Repository("bookDao")
public class BookDaolmpl implements IBookDao{

	@Autowired
	private SessionFactory sessionFactory;
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	@Override
	public Long save(Book book) {
		// TODO Auto-generated method stub
		return (Long) getSession().save(book);
	}

	@Override
	public List<Book> findBookWithWhere(String where) {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Book as b where"+where).getResultList();
	}

	@Override
	public List<Book> findAllBooks() {
		// TODO Auto-generated method stub
		return findBookWithWhere("1=1");
	}

	@Override
	public void delete(Book book) {
		// TODO Auto-generated method stub
		getSession().delete(book);
	}

	@Override
	public Book findById(Long id) {
		// TODO Auto-generated method stub
		return getSession().get(Book.class, id);
	}

	@Override
	public void updata(Book book) {
		// TODO Auto-generated method stub
		getSession().merge(book);
	}

}
