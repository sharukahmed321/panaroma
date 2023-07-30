package com.example.project.panaroma.Service;

import com.example.project.panaroma.Model.BankDetailsModel;
import com.example.project.panaroma.Model.MemberModel;
import com.example.project.panaroma.Repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class BankDetailService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    private final MemberService memberService;

    @Autowired
    public BankDetailService(MemberRepository memberRepository, ModelMapper modelMapper, MemberService memberService) {
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
        this.memberService = memberService;
    }

    /**
     * This Function Will get All Banks
     *
     * @return
     */
    public List<BankDetailsModel> getAllBanks(Long memberId){
        List<BankDetailsModel> bankDetailsModels = new ArrayList<>();
        MemberModel memberModel = memberService.getMemberById(memberId);
        return memberModel.getBanks();
    }

    /**
     * This function will help in add new Bank for a member in DB
     *
     * @param bankDetailsModel
     */
    public List<BankDetailsModel> addBank(Long memberId, BankDetailsModel bankDetailsModel){
        List<BankDetailsModel> allBanks = getAllBanks(memberId);
        allBanks.add(bankDetailsModel);
        return allBanks;
    }

    /**
     * This function will help to update a bank in DB
     *
     * @param bankDetailsModel
     * @return
     */
    public List<BankDetailsModel> editBankDetails(Long memberId, BankDetailsModel bankDetailsModel){
        if(bankDetailsModel == null) return new ArrayList<>();
        Long bankId = bankDetailsModel.getBankId();
        List<BankDetailsModel> allBanks = getAllBanks(memberId);
        for(int i=0;i< allBanks.size();i++){
            BankDetailsModel bank = allBanks.get(i);
            if(Objects.equals(bank.getBankId(), bankId)){
                allBanks.set(i,bankDetailsModel);
            }
        }
        return allBanks;
    }

    /**
     * This function will help us to delete a bank from DB
     *
     * @param memberId
     */
    public List<BankDetailsModel> deleteBankDetails(Long memberId, Long bankId){
        if(bankId == null) return new ArrayList<>();
        List<BankDetailsModel> allBanks = getAllBanks(memberId);
        for(int i=0;i< allBanks.size();i++){
            BankDetailsModel bank = allBanks.get(i);
            if(Objects.equals(bank.getBankId(), bankId)){
                allBanks.remove(bank);
            }
        }
        return allBanks;
    }

}
