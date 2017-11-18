package library.springmvc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import library.springmvc.model.Book;
import library.springmvc.model.Borrow;
import library.springmvc.model.Operator;
import library.springmvc.model.Reader;
import library.springmvc.model.ReaderType;
import library.springmvc.service.IBookService;
import library.springmvc.service.IOperatorService;
import library.springmvc.service.IReaderService;

@Controller("/operator")
public class OperatorController {
	@Autowired
	private IOperatorService operService;
	@Autowired
	private IBookService bookService;
	@Autowired
	private IReaderService readerService;

	@RequestMapping(method = RequestMethod.GET)
	public String showLogin(){
		return "operator/login";
	}
	@RequestMapping("login")
	public String login(@RequestParam("login") String login,
			@RequestParam("pwd") String pwd, HttpSession session,Model model) {
		List<Operator> list=operService.findByColumn("loginName", login);
		if(list.size()>0){
			if(list.get(0).getPwd().equals(pwd))
				session.setAttribute("currentOperator", list.get(0));
			else{
				model.addAttribute("msg", "密码错误，请重新输入");
				return "operator/login";
			}
		}else{
			model.addAttribute("msg", "登录名不存在，请重新输入");
			return "operator/login";
		}
		model.addAttribute("msg","success");
		model.addAttribute("operator",new Operator());
		model.addAttribute("reader",new Reader());
		model.addAttribute("book",new Book());
		return "operator/home";
	}
	@RequestMapping(value="newOper", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String newOper(@ModelAttribute("operator")Operator oper) {
		JSONObject json =new JSONObject();
		try{
			operService.addOperator(oper);
			json.put("flag", true);
			json.put("msg", "管理员创建成功");
		}catch(Exception e){
			e.printStackTrace();
			json.put("flag", false);
			json.put("msg", e.getMessage());
		}
		return json.toString();
	}
	@RequestMapping(value="checkLoginName", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String checkLoginName(@RequestParam("loginName") String login,@RequestParam("opId") Long opId) {
		JSONObject json =new JSONObject();
		try{
			List<Operator> list=operService.findByColumn("loginName", login);
			if(list.size()>0&&list.get(0).getOperId()!=opId){
				json.put("flag", false);
				json.put("msg", "登录名已存在 请重新输入一个");
			}
			else
				json.put("flag", true);
		}catch(Exception e){
			e.printStackTrace();
			json.put("flag", false);
			json.put("msg", e.getMessage());
		}
		return json.toString();
	}
	@RequestMapping(value="qryOper", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String qryOper(HttpServletRequest req) {
		JSONObject json =new JSONObject();
		List<?> list=null;
		String where="";
		String name=req.getParameter("qryOperName");
		String login=req.getParameter("qryLoginName");
		String phone=req.getParameter("qryPhone");
		if(name!=null&&name.length()>0)
			where+="operName like '%"+name+"%' and ";
		if(login!=null&&login.length()>0)
			where+="loginName like '%"+login+"%' and ";
		if(phone!=null&&phone.length()>0)
			where+="phone like '%"+phone+"%' and ";
		try{
			if(where.length()>0){
				where +=" 1=1";
				list=operService.findWithWhere(where);
			}else{
				list=operService.findAll();
			}
			json.put("flag",true);
		}catch(Exception e){
			e.printStackTrace();
			json.put("flag",false);
			json.put("msg", e.getMessage());
		}
		json.put("list", list);
		return json.toString();
	}
	@RequestMapping(value="delOper", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delOper(@RequestParam("opId") Long opId) {
		JSONObject json =new JSONObject();
		try{
			Operator oper=new Operator();
			oper.setOperId(opId);
			operService.delete(oper);
			json.put("flag", true);
		}catch(Exception e){
			e.printStackTrace();
			json.put("flag", false);
			json.put("msg", e.getMessage());
		}
		return json.toString();
	}
	@RequestMapping(value="modifyOper", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String modifyOper(@ModelAttribute("operator")Operator oper) {
		JSONObject json =new JSONObject();
		try{
			operService.update(oper);
			json.put("flag", true);
			json.put("msg", "修改管理员成功");
		}catch(Exception e){
			e.printStackTrace();
			json.put("flag", false);
			json.put("msg", e.getMessage());
		}
		return json.toString();
	}
	@RequestMapping(value="initBookType", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String initBookType() {
		JSONObject json =new JSONObject();
		List<?> list=null;
		try{
			list=bookService.getAllBookType();
			json.put("flag",true);
		}catch(Exception e){
			e.printStackTrace();
			json.put("flag",false);
			json.put("msg", e.getMessage());
		}
		json.put("list", list);
		return json.toString();
	}
	@RequestMapping(value="initPublish", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String initPublish() {
		JSONObject json =new JSONObject();
		List<?> list=null;
		try{
			list=bookService.getAllPublisher();
			json.put("flag",true);
		}catch(Exception e){
			e.printStackTrace();
			json.put("flag",false);
			json.put("msg", e.getMessage());
		}
		json.put("list", list);
		return json.toString();
	}
	@RequestMapping(value="newBook", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String newBook(@ModelAttribute("book")Book book) {
		JSONObject json =new JSONObject();
		try{
			bookService.addBook(book);
			json.put("flag", true);
			json.put("msg", "图书入库成功");
		}catch(Exception e){
			e.printStackTrace();
			json.put("flag", false);
			json.put("msg", e.getMessage());
		}
		return json.toString();
	}
	@RequestMapping(value="qryBook", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String qryBook(HttpServletRequest req) {
		JSONObject json =new JSONObject();
		List<?> list=null;
		String where="";
		String isbn=req.getParameter("ISBN");
		String bookName=req.getParameter("bookName");
		String bookTypeId=req.getParameter("bookTypeId");
		String publisherId=req.getParameter("publisherId");
		String author=req.getParameter("author");
		if(isbn!=null&&isbn.length()>0)
			where+="ISBN = '"+isbn+"' and ";
		if(bookName!=null&&bookName.length()>0)
			where+="bookName like '%"+bookName+"%' and ";
		if(bookTypeId!=null&&bookTypeId.length()>0)
			where+="bookTypeId = "+bookTypeId+" and ";
		if(publisherId!=null&&publisherId.length()>0)
			where+="publisherId = "+publisherId+" and ";
		if(author!=null&&author.length()>0)
			where+="author like '%"+author+"%' and ";
		try{
			if(where.length()>0){
				where +=" 1=1";
				list=bookService.findBookWithWhere(where);
			}else{
				list=bookService.findAllBooks();
			}
			json.put("flag",true);
		}catch(Exception e){
			e.printStackTrace();
			json.put("flag",false);
			json.put("msg", e.getMessage());
		}
		json.put("list", list);
		return json.toString();
	}
	@RequestMapping(value="delBook", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delBook(@RequestParam("bookId") Long bookId) {
		JSONObject json =new JSONObject();
		try{			
			Book book=bookService.findBookById(bookId);
			if(book.getCurrentNum()!=book.getTotalNum()){
				json.put("flag", false);
				json.put("msg", "图书有借出，不能删除");
				return json.toString();
			}
			book.setId(bookId);
			bookService.delete(book);
			json.put("flag", true);
		}catch(Exception e){
			e.printStackTrace();
			json.put("flag", false);
			json.put("msg", e.getMessage());
		}
		return json.toString();
	}
	@RequestMapping(value="modBook", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String modBook(@ModelAttribute("book")Book book) {
		JSONObject json =new JSONObject();
		try{
			bookService.update(book);
			json.put("flag", true);
			json.put("msg", "修改图书信息成功");
		}catch(Exception e){
			e.printStackTrace();
			json.put("flag", false);
			json.put("msg", e.getMessage());
		}
		return json.toString();
	}
	@RequestMapping(value="initReaderType", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String initReaderType() {
		JSONObject json =new JSONObject();
		List<ReaderType> list=null;
		try{
			list=readerService.getAllReaderType();
			json.put("flag",true);
		}catch(Exception e){
			e.printStackTrace();
			json.put("flag",false);
			json.put("msg", e.getMessage());
		}
		json.put("list", list);
		return json.toString();
	}
	@RequestMapping(value="newReader", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String newReader(@ModelAttribute("reader")Reader reader) {
		JSONObject json =new JSONObject();
		try{
			reader.setBorrowNum((short) 0);
			readerService.addReader(reader);
			json.put("flag", true);
			json.put("msg", "新建读者成功");
		}catch(Exception e){
			e.printStackTrace();
			json.put("flag", false);
			json.put("msg", e.getMessage());
		}
		return json.toString();
	}
	@RequestMapping(value="qryReader", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String qryReader(HttpServletRequest req) {
		JSONObject json =new JSONObject();
		List<Reader> list=new ArrayList<Reader>();
		String where="";
		String readerId=req.getParameter("qryReaderId");
		String name=req.getParameter("qryName");
		String readerType=req.getParameter("qryReaderType");
		String identityNum=req.getParameter("qryIdentityNum");
		String phone=req.getParameter("qryReaderPhone");
		
		if(name!=null&&name.length()>0)
			where+="name like '%"+name+"%' and ";
		if(readerType!=null&&readerType.length()>0)
			where+="readerType= "+readerType+" and ";
		if(phone!=null&&phone.length()>0)
			where+="phone like '%"+phone+"%' and ";
		if(identityNum!=null&&identityNum.length()>0)
			where+="identityNum = '"+identityNum+"' and ";
		try{
			if(readerId!=null&&readerId.length()>0){
				list.add(readerService.findReaderById(Long.valueOf(readerId)));
			}else{
				if(where.length()>0){
					where +=" 1=1";
					list=readerService.findReaderWithWhere(where);
				}else{
					list=readerService.findAllReader();
				}
			}
			json.put("flag",true);
		}catch(Exception e){
			e.printStackTrace();
			json.put("flag",false);
			json.put("msg", e.getMessage());
		}
		json.put("list", list);
		return json.toString();
	}
	@RequestMapping(value="delReader", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delReader(@RequestParam("readerId") Long readerId) {
		JSONObject json =new JSONObject();
		try{
			Reader reader=new Reader();
			reader.setReaderId(readerId);
			readerService.delete(reader);
			json.put("flag", true);
		}catch(Exception e){
			e.printStackTrace();
			json.put("flag", false);
			json.put("msg", e.getMessage());
		}
		return json.toString();
	}
	@RequestMapping(value="getBorrowed", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getBorrowed(@RequestParam("readerId") Long readerId) {
		JSONObject json =new JSONObject();
		List<Borrow> list=null;
		try{
			list=readerService.getReaderBorrowed(readerId);
			json.put("flag",true);
		}catch(Exception e){
			e.printStackTrace();
			json.put("flag",false);
			json.put("msg", e.getMessage());
		}
		json.put("list", list);
		return json.toString();
	}
	@RequestMapping(value="borrowBook", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String borrowBook(@RequestParam("readerId") Long readerId,@RequestParam("bookCode") String bookCode,HttpSession session) {
		JSONObject json =new JSONObject();
		Operator op=(Operator) session.getAttribute("currentOperator");
		try{
			int ret=readerService.checkBorrow(readerId,bookCode);
			if(ret==0){
				bookService.addBorrow(readerId,bookCode, op.getOperId());
				json.put("flag", true);
				json.put("msg", "借书成功");
			}else if(ret==1){
				json.put("flag", false);
				json.put("msg", "读者所借书已达可借图书上限,不能继续借书");
			}else if(ret==2){
				json.put("flag", false);
				json.put("msg", "读者已经借阅了该书,不能重复借书");
			}else{
				json.put("flag", false);
				json.put("msg", "未知错误,不能继续借书");
			}
		}catch(Exception e){
			e.printStackTrace();
			json.put("flag", false);
			json.put("msg", e.getMessage());
		}
		return json.toString();
	}
	@RequestMapping(value="returnBook", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String returnBook(@RequestParam("bookCode") String bookCode,HttpSession session) {
		JSONObject json =new JSONObject();
		Operator op=(Operator) session.getAttribute("currentOperator");
		try{
			bookService.updateBorrow(bookCode,op.getOperId());
			json.put("flag", true);
			json.put("msg", "还书成功");
		}catch(Exception e){
			e.printStackTrace();
			json.put("flag", false);
			json.put("msg", e.getMessage());
		}
		return json.toString();
	}
	@RequestMapping("GetOperType")
	@ResponseBody
	public String GetOperType() {
		return "operator/home";
	}
}
