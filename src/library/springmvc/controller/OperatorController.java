package library.springmvc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import library.springmvc.model.Book;
import library.springmvc.model.Operator;
import library.springmvc.model.Reader;
import library.springmvc.service.IOperatorService;

@Controller("/operator")
public class OperatorController {
	@Autowired
	private IOperatorService operService;
	@RequestMapping(method=RequestMethod.GET)
	public String showLogin() {
		return "operator/login";
	}
	@RequestMapping("login")
	public String login(@RequestParam("login")String login,
			@RequestParam("pwd")String pwd,HttpSession session,Model model) {
		List<Operator>list=operService.findByColumn("loginName", login);
		if(list.size()>0) {
			if(list.get(0).getPwd().equals(pwd))
				session.setAttribute("currentOperator", list.get(0));
			else {
				model.addAttribute("msg", "密码错误，请重新输入");
				return "operator/login";
			}
		}
		else {
			model.addAttribute("msg", "登录名不存在，请重新输入");
			return "operator/login";
		}
		model.addAttribute("msg", "success");
		model.addAttribute("operator", new Operator());
		model.addAttribute("reader", new Reader());
		model.addAttribute("book", new Book());
		return "operator/home";
	}

}
