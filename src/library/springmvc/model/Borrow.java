package library.springmvc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name="borrow")
public class Borrow {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	@Column
	private Long readerId;
	@Column
	private String bookCode;
	@Column
	private Long borrowOperId;
	@Column
	@DateTimeFormat(iso = ISO.DATE)
	private Date borrowDate;
	@Column
	@DateTimeFormat(iso = ISO.DATE)
	private Date lastDate;
	@Column
	private Short isRenew;
	@Column
	private Short isReturn;
	@Column
	@DateTimeFormat(iso = ISO.DATE)
	private Date returnDate;
	@Column
	private Long returnOperId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id=id;
	}
	public Long getReaderId() {
		return readerId;
	}
	public void setReaderId(Long readerId) {
		this.readerId=readerId;
	}
	public String getBookCode() {
		return bookCode;
	}
	public void setBookCode(String bookCode) {
		this.bookCode=bookCode;
	}
	public Long getBorrowOperId() {
		return borrowOperId;
	}
	public void setBorrowOperId(Long borrowOperId) {
		this.borrowOperId=borrowOperId;
	}
	public Date getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(Date borrowDate) {
		this.borrowDate=borrowDate;
	}
	public Date getLastDate() {
		return lastDate;
	}
	public void setLastDate(Date lastDate) {
		this.lastDate=lastDate;
	}
	public Short getIsRenew() {
		return isRenew;
	}
	public void setIsRenew(Short isRenew) {
		this.isRenew=isRenew;
	}
	public Short getIsReturn() {
		return isReturn;
	}
	public void setIsReturn(Short isReturn) {
		this.isReturn=isReturn;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate=returnDate;
	}
	public Long getReturnOperId() {
		return returnOperId;
	}
	public void setReturnOperId(Long returnOperId) {
		this.returnOperId=returnOperId;
	}
	
}
