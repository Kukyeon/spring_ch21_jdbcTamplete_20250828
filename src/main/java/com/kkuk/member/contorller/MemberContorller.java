package com.kkuk.member.contorller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kkuk.member.dao.MemberDao;
import com.kkuk.member.dto.MemberDto;

@Controller
public class MemberContorller {
	
	@Autowired
	private MemberDao memberDao;
	
	@RequestMapping(value = "/join") // 회원가입
	public String join() {
		return "join";
	}
	
	@RequestMapping(value = "/joinOk")
	public String joinOk(HttpServletRequest request, Model model) {
		
		String mid = request.getParameter("memberid");
		String mpw = request.getParameter("memberpw");
		String mname = request.getParameter("membername");
		int mage = Integer.parseInt(request.getParameter("memberage"));
		
		memberDao.insertMember(mid, mpw, mname, mage);
		
		return "redirect:memberlist";
	}
	
	@RequestMapping(value = "/memberlist")
	public String memberlist(Model model) {
		
		List<MemberDto> mDtos = memberDao.searchMembers();
		model.addAttribute("mDtos", mDtos);
		
		return "memberlist";
	}
	
	@RequestMapping(value = "/search")
	public String search() {
		return "searchMember";
	}
	
	@RequestMapping(value = "/searchOk")
	public String searchOk(HttpServletRequest request, Model model) {
		
		MemberDto mDto = memberDao.searchMember(request.getParameter("memberid"));
		model.addAttribute("mDto", mDto);
		model.addAttribute("result", "1");
		
		return "searchMember";
	}
}
