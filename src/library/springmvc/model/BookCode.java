package library.springmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="book_code")
public class BookCode {
	@Id
	@Column
	private String id;
	@Column
	private Long bookId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id=id;
	}
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId=bookId;
	}

}
