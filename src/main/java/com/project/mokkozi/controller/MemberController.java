package com.project.mokkozi.controller;

import com.project.mokkozi.entity.Member;
import com.project.mokkozi.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/member")
/*
    Create : POST (Body O)
        - /member : 새로운 member 데이터 생성
    Read : GET (Body X)
        - /member : Post 테이블의 모든 데이터 보여주기
        - /member?id={memberId}: userId가 일치하는 member 데이터 보여주기
    Update : PUT/PATCH (Body O)
        - /member/{id}: id가 일치하는 member 데이터 수정
    Delete : DELETE (Body X)
        - /member/{id}: id가 일치하는 member 데이터 삭제
 */
public class MemberController {

    @Autowired
    private MemberService memberService;

    /**
     * [createMember] 사용자 생성 및 생성된 사용자 반환
     * <p>
     * @param member 생성할 사용자 정보
     * @return 생성된 사용자 정보
     */
    @PostMapping
    public @ResponseBody ResponseEntity<Member> createMember(@RequestBody Member member) {
        return ResponseEntity.ok(memberService.createMember(member));
    }

    /**
     * [readMembers] 사용자 정보 조회
     * <p>
     * @param id 조회할 사용자 id (선택)
     * @return param으로 id가 넘어올 경우 해당 사용자 조회, 없을 경우 모든 사용자 조회
     */
    @GetMapping
    public @ResponseBody ResponseEntity readMembers(@RequestParam(value = "id", required = false) Long id) {
        if(id != null) { // member 단일 조회
            return ResponseEntity.ok(memberService.readMember(id));
        }
        return ResponseEntity.ok(memberService.readMembers());
    }

    /*@GetMapping
    public String readMembers(@RequestParam(value = "id", required = false) Long id, Model model) {
        if(id != null) { // member 단일 조회
            model.addAttribute("members", memberService.readMember(id));
//          return ResponseEntity.ok(memberService.readMember(id));
        } else {
            model.addAttribute("members", memberService.readMembers());
        }
        return "/list";
//        return ResponseEntity.ok(memberService.readMembers());
    }*/

    /**
     * [updateMember] id에 해당하는 member 정보 수정
     * <p>
     * @param id 조회할 사용자명
     * @param member 수정할 정보가 담긴 member 객체
     * @return 사용자 정보가 존재하지 않을 경우 EntityNotFoundException, 존재할 경우 값 수정(set)
     */
    @PatchMapping
    public ResponseEntity<Member> updateMember (@PathVariable @RequestParam(value = "id") Long id, @RequestBody Member member) {
        return ResponseEntity.ok(memberService.updateMember(id, member));
    }

    /**
     * [deleteMember] id에 해당하는 사용자 삭제
     * <p>
     * @param id 삭제할 사용자 id
     * @return 사용자 정보가 존재할 경우 해당 id 삭제, 그렇지 않을 경우 null 반환
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteMember(@PathVariable @RequestParam(value = "id") Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok().build();
    }

}
