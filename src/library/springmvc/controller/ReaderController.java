package library.springmvc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import library.springmvc.model.Borrow;
import library.springmvc.model.Reader;
import library.springmvc.service.IReaderService;

@Controller()
public class ReaderController {
	@Autowired
	private IReaderService readerService;
	@RequestMapping("reader")
	public String readerLogin(){
		return "reader/login";
	}
	@RequestMapping("dologin")
	public String doLogin(@RequestParam("readerId") Long readerId,
			@RequestParam("pwd") String pwd, HttpSession session,Model model) {
		Reader reader=readerService.findReaderById(readerId);
		if(reader==null){
			model.addAttribute("msg", "读者卡号不存在，请重新输入");
			return "reader/login";
		}
		if(reader.getPwd().equals(pwd))
			session.setAttribute("currentReader", reader);
		else{
			model.addAttribute("msg", "密码错误，请重新输入");
			return "reader/login";
		}
		List<Borrow> list=readerService.getReaderBorrowed(readerId);
		JSONObject json =new JSONObject();
		json.put("list", list);
		model.addAttribute("borrowedBook",json.toString());
		return "reader/home";
	}
	@RequestMapping(value="renewBook", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String renewBook(@RequestParam("bookCode") String bookCode,HttpSession session) {
		JSONObject json =new JSONObject();
		Reader r=(Reader) session.getAttribute("currentReader");
		try{
			readerService.updateBorrowRenew(r.getReaderId(),bookCode);
			json.put("flag", true);
			json.put("msg", "续借成功");
		}catch(Exception e){
			e.printStackTrace();
			json.put("flag", false);
			json.put("msg", e.getMessage());
		}
		return json.toString();
	}
	@RequestMapping(value="chgPwd", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String chgPwd(@RequestParam("srcpwd") String srcpwd,@RequestParam("newpwd") String newpwd,HttpSession session) {
		JSONObject json =new JSONObject();
		Reader r=(Reader) session.getAttribute("currentReader");
		try{
			if(r.getPwd().equals(srcpwd)){
				readerService.updateReaderPwd(r.getReaderId(),newpwd);
				json.put("flag", true);
				json.put("msg", "修改密码成功");
			}else{
				json.put("flag", false);
				json.put("msg", "原密码不正确,不能修改密码");
			}
		}catch(Exception e){
			e.printStackTrace();
			json.put("flag", false);
			json.put("msg", e.getMessage());
		}
		return json.toString();
	}

	@RequestMapping(value="myBorrowed", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String myBorrowed(HttpSession session) {
		JSONObject json =new JSONObject();
		Reader r=(Reader) session.getAttribute("currentReader");
		try{
			List<Borrow> list=readerService.getReaderBorrowed(r.getReaderId());
			json.put("flag", true);
			json.put("list", list);
		}catch(Exception e){
			e.printStackTrace();
			json.put("flag", false);
			json.put("msg", e.getMessage());
		}
		return json.toString();
	}
	@RequestMapping(value="logout", produces = "text/html;charset=UTF-8")
	public String logout(HttpSession session) {
		session.removeAttribute("currentReader");
		return "reader/login";
	}
}
