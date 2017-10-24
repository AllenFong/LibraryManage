package library.springmvc.dao;

import java.util.List;

import library.springmvc.model.ReaderType;

public interface IReaderTypeDao {
	public List<ReaderType> finAll();
	public ReaderType findById(Short readerType);

}
