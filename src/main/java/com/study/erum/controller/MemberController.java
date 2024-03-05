package com.study.erum.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.erum.dto.MemberDTO;
import com.study.erum.repository.MemberRepository;
import com.study.erum.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;

	
	@GetMapping("/save")
	public String saveForm() {
		return "save";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute MemberDTO memberDTO) {
		 int saveResult = memberService.save(memberDTO);
		    if(saveResult > 0){
		      return "login";
		    }else{
		      return "save";
		    }
	}
	
	@GetMapping("/login")
	public String loginForm() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
		boolean loginResult = memberService.login(memberDTO);
		System.out.println(loginResult);
		if(loginResult) {
			session.setAttribute("loginEmail", memberDTO.getMemberEmail());
			return "main";
		}else {
			return "login";
		}
	}
	@GetMapping("/")
	public String findAll(Model model) {
		List<MemberDTO> memberDTOList = memberService.findAll();
		model.addAttribute("memberList",memberDTOList);
		return "list";
	}
	
	// /member?id=1
	@GetMapping
	public String findById(@RequestParam("id") Long id, Model model) {
		MemberDTO memberDTO = memberService.findById(id);
		model.addAttribute("member",memberDTO);
		return "detail";
	}
	
	@GetMapping("/delete")
	  public String delete(@RequestParam("id") Long id){
	    memberService.delete(id);
	    return "redirect:/member/";
	  }
	//수정화면
	   @GetMapping("/update")
	   public String updateForm(HttpSession session, Model model) {
	      //세션에 저장된 나의 이메일 가져오기
	      String loginEmail = (String)session.getAttribute("loginEmail");
	      MemberDTO memberDTO = memberService.findByMemberEmail(loginEmail);
	      model.addAttribute("member",memberDTO);
	      return "update";
	   }
}
