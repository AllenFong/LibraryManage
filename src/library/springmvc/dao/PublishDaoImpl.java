package library.springmvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import library.springmvc.model.Publisher;
@Repository("pubDao")
public class PublishDaoImpl implements IPublisherDao {

	@Autowired
	private SessionFactory sessionFactory;
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	@Override
	public List<Publisher> findAll() {
		return getSession().createQuery("from Publisher").getResultList();
	}

}
