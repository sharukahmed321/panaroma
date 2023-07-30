package com.example.project.panaroma.Controller;

import com.example.project.panaroma.Entity.MemberEntity;
import com.example.project.panaroma.Model.MemberModel;
import com.example.project.panaroma.Service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * This Api is to Fetch all Members
     * @return
     */
    @GetMapping("/members")
    public List<MemberModel> getAllMembers(){
        return memberService.getMembers();
    }

    /**
     * This Api is used to fetch Member Object from given MemberId
     *
     * @param memberId
     * @return
     */
    @GetMapping("/members/getMember/{id}")
    public MemberModel getMemberById(@PathVariable("id") Long memberId){
        if(memberId == null){
            log.info("Member Id can't be null");
            return new MemberModel();
        }
        return memberService.getMemberById(memberId);
    }

    /**
     * Create a new Member with this Api
     *
     * @param memberModel
     */
    @PostMapping("/members/create")
    public void createMember(@RequestBody MemberModel memberModel){
        if(memberModel == null){
            log.info("Member object can't be null");
            return;
        }
        memberService.addMember(memberModel);
    }

    /**
     * Update Details with this Api
     *
     * @param memberModel
     * @return
     */
    @PutMapping("/members/update")
    public MemberEntity editMember(@RequestBody MemberModel memberModel){
        if(memberModel == null){
            log.info("Member object can't be null");
            return new MemberEntity();
        }
        return memberService.editMember(memberModel);
    }

    /**
     * Delete a Member Object in DB by using the following memberId
     *
     * @param memberId
     */
    @DeleteMapping("members/delete/{id}")
    public void deleteMember(@PathVariable("id") Long memberId){
        if(memberId == null){
            log.info("Member Id can't be null");
            return;
        }
        memberService.deleteMember(memberId);
    }


    /**
     * Get All Members Who has a bank Account in a given country
     *
     * @param country
     * @return
     */
    public List<MemberModel> getAllMembersWithBankAccInCountry(@RequestParam String country){
        if(country == null){
            log.info("country name is null, can't fetch information");
        } else {
            return memberService.getAllMembersWithBankAccountInACountry(country);
        }
        return null;
    }

}
