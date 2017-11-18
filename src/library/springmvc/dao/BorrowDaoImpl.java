package library.springmvc.dao;

import java.util.List;

import library.springmvc.model.Borrow;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import library.springmvc.model.Book;
@Repository("borrowDao")
public class BorrowDaoImpl implements IBorrowDao {

	@Autowired
	private SessionFactory sessionFactory;
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	@Override
	public List<Borrow> getBorrowedByReaderId(Long readerId) {
		return getSession().createQuery("from Borrow where readerId="+readerId +" and isReturn=0").getResultList();
	}

	@Override
	public void save(Borrow borrow) {
		getSession().save(borrow);		
	}

	@Override
	public void update(Borrow borrow) {
		getSession().update(borrow);
	}
	@Override
	public Borrow getBorrowedByBookCode(String bookCode) {
		return (Borrow) getSession().createQuery("from Borrow where bookCode='"+bookCode +"' and isReturn=0").getSingleResult();
	}

}
