package library.springmvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import library.springmvc.model.BookType;

@Repository("bookTypeDao")
public class BookTypeDaoImpl implements IBookTypeDao {

	@Autowired
	private SessionFactory sessionFactory;
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	@Override
	public List<BookType> findAll() {		
		return getSession().createQuery("from BookType").getResultList();
	}

}
