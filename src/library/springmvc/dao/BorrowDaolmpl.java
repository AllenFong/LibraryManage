package library.springmvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import library.springmvc.model.Borrow;

@Repository("borrowDao")
public class BorrowDaolmpl implements IBorrowDao{
	@Autowired
	private SessionFactory sessionFactory;
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<Borrow> getBorrowedByReaderId(Long readerId) {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Borrow where readerId="+readerId+" and isReturn=0").getResultList();
	}

	@Override
	public void save(Borrow borrow) {
		// TODO Auto-generated method stub
		getSession().save(borrow);
	}

	@Override
	public void update(Borrow borrow) {
		// TODO Auto-generated method stub
		getSession().update(borrow);
	}

	@Override
	public Borrow getBorrowedByBookCode(String bookCode) {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Borrrow where="+bookCode+" and isReturn=0").;
	}

}
