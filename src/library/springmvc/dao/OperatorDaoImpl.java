package library.springmvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import library.springmvc.model.Operator;
@Repository("operDao")
public class OperatorDaoImpl implements IOperatorDao {
	@Autowired
	private SessionFactory sessionFactory;
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(Operator oper) {
		getSession().save(oper);
	}

	@Override
	public List<Operator> findAll() {
		return findWithWhere("1=1");
	}

	@Override
	public List<Operator> findByColumn(String colName, String value) {
		String sql="from Operator as o where o."+colName+"=?";		
		return getSession().createQuery(sql).setParameter(0, value).getResultList();
	}

	@Override
	public void update(Operator oper) {
		getSession().update(oper);

	}

	@Override
	public void delete(Operator oper) {
		getSession().delete(oper);

	}

	@Override
	public Operator findById(Long id) {
		return getSession().get(Operator.class, id);
	}

	@Override
	public List<Operator> findWithWhere(String strWhere) {
		String sql="from Operator as o where "+strWhere;		
		return getSession().createQuery(sql).getResultList();
	}

}
