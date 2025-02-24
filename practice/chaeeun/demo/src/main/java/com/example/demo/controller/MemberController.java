package com.example.demo.controller;

import com.example.demo.dto.AuthorDTO;
import com.example.demo.dto.MemberDTO;
import com.example.demo.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원 생성
    @PostMapping
    public MemberDTO createMember(@RequestBody MemberDTO memberDTO) {
        return memberService.registerMember(memberDTO);
    }

    // id로 회원 조회
    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> getMember(@PathVariable("id") Long id) {
        return memberService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // id로 회원 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable("id") Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build(); // 204 no content
    }

    // 회원이 대여 중인 책 목록 조회
}
