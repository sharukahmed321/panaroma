package com.example.project.panaroma.Service;

import com.example.project.panaroma.Entity.MemberEntity;
import com.example.project.panaroma.Model.BankDetailsModel;
import com.example.project.panaroma.Model.MemberModel;
import com.example.project.panaroma.Repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MemberService(MemberRepository memberRepository, ModelMapper modelMapper) {
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * This Function Will get All Members
     *
     * @return
     */
    public List<MemberModel> getMembers(){
        List<MemberModel> memberModels = new ArrayList<>();
        List<MemberEntity> memberEntities = memberRepository.findAll();
        for(MemberEntity memberEntity : memberEntities){
            MemberModel member = modelMapper.map(memberEntity, MemberModel.class);
            memberModels.add(member);
        }
        return memberModels;
    }


    /**
     * This function helps us to fetch member from MemberId
     *
     * @param memberId
     * @return
     */
    public MemberModel getMemberById(Long memberId){
        Optional<MemberEntity> memberEntityOpt = memberRepository.findById(memberId);
        MemberEntity memberEntity = memberEntityOpt.orElse(null);
        return modelMapper.map(memberEntity, MemberModel.class);
    }

    /**
     * This function will help in add new Member to DB
     *
     * @param memberModel
     */
    public void addMember(MemberModel memberModel){
        if(memberModel == null) return;
        MemberEntity memberEntity = modelMapper.map(memberModel, MemberEntity.class);
        memberRepository.save(memberEntity);
    }

    /**
     * This function will help to update a member in DB
     *
     * @param memberModel
     * @return
     */
    public MemberEntity editMember(MemberModel memberModel){
        if(memberModel == null) return new MemberEntity();
        MemberEntity memberEntity = modelMapper.map(memberModel, MemberEntity.class);
        memberRepository.save(memberEntity);
        return memberEntity;
    }

    /**
     * This function will help us to delete a member from DB
     *
     * @param memberId
     */
    public void deleteMember(Long memberId){
        Optional<MemberEntity> memberEntityOpt = memberRepository.findById(memberId);
        MemberEntity memberEntity = memberEntityOpt.orElse(null);
        if(memberEntity == null){
            log.info("Can't delete because member Entity is null");
        } else {
            memberRepository.delete(memberEntity);
        }
    }

    /**
     * Get All members who has a bank Account in a given Country
     *
     * @param countryName
     * @return
     */
    public List<MemberModel> getAllMembersWithBankAccountInACountry(String countryName){
        List<MemberModel> members = getMembers();
        List<MemberModel> result = new ArrayList<>();
        for(MemberModel member : members){
            List<BankDetailsModel> allBanks = member.getBanks();
            for(BankDetailsModel bank : allBanks){
                if(bank.getBankAccountCountry().equals(countryName)){
                    result.add(member);
                }
            }
        }
        return result;
    }
}
