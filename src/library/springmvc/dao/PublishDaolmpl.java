package library.springmvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import library.springmvc.model.Publisher;

@Repository("pubDao")
public class PublishDaolmpl implements IPublisherDao{
	@Autowired
	private SessionFactory sessionFactory;
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	

	@Override
	public List<Publisher> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
