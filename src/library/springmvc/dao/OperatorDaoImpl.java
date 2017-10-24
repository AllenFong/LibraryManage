package library.springmvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import library.springmvc.model.Operator;

public class OperatorDaoImpl implements IOperatorDao{
	@Autowired
	private SessionFactory sessionFactory;
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(Operator oper) {
		// TODO Auto-generated method stub
		getSession().save(oper);
	}

	@Override
	public List<Operator> findAll() {
		// TODO Auto-generated method stub
		return findWithWhere("1=1");
	}

	@Override
	public List<Operator> findWithWhere(String strWhere) {
		// TODO Auto-generated method stub
		String sql="from Operator as o where "+strWhere;
		return  getSession().createQuery(sql).getResultList();
	}

	@Override
	public List<Operator> findByColumn(String colName, String value) {
		// TODO Auto-generated method stub
		String sql="from Operator as o where o."+colName+"=?";
		return getSession().createQuery(sql).setParameter(0,value).getResultList();
	}

	@Override
	public void update(Operator oper) {
		// TODO Auto-generated method stub
		getSession().update(oper);
	}

	@Override
	public void delete(Operator oper) {
		// TODO Auto-generated method stub
		getSession().delete(oper);
	}

	@Override
	public Operator findById(Long id) {
		// TODO Auto-generated method stub
		return getSession().get(Operator.class, id);
	}
}
