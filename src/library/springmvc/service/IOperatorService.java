package library.springmvc.service;

import java.util.List;

import library.springmvc.model.Operator;

public interface IOperatorService {
	public void addOperator(Operator oper);
	public List<Operator> findAll();
	public List<Operator> findWithWhere(String strWhere);
	public List<Operator> findByColumn(String colName,String value);
	public void update(Operator oper);
	public void delete(Operator oper);
	public Operator findById(Long id);

}
