package library.springmvc.dao;

import java.util.List;

import library.springmvc.model.Publisher;

public interface IPublisherDao {
	public List<Publisher> findAll();
}
