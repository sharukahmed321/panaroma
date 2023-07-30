package com.example.project.panaroma.Controller;

import com.example.project.panaroma.Model.BankDetailsModel;
import com.example.project.panaroma.Service.BankDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class BankDetailsController {

    private final BankDetailService bankDetailService;

    @Autowired
    public BankDetailsController(BankDetailService bankDetailService) {
        this.bankDetailService = bankDetailService;
    }

    /**
     * get all banks with the help of memberId
     *
     * @param id
     * @return
     */
    @GetMapping("/banks/memberId/{id}")
    public List<BankDetailsModel> getAllMembers(@PathVariable("id") Long id){
        return bankDetailService.getAllBanks(id);
    }

    /**
     * Create a new bank and add to the existing banks list by fetch list from memberId
     *
     * @param memberId
     * @param bankDetailsModel
     */
    @PostMapping("/banks/createBank/memberId/{id}")
    public void createBank(@PathVariable("id") Long memberId, @RequestBody BankDetailsModel bankDetailsModel){
        if(memberId == null || bankDetailsModel == null){
            log.info("Member Id or bank object is null");
            return;
        }
        bankDetailService.addBank(memberId, bankDetailsModel);
    }


    /**
     * Edit a existing bank account details with the help of memberId to fetch all bank wrt member
     *
     * @param memberId
     * @param bankDetailsModel
     */
    @PutMapping("/banks/updateBank/memberId/{id}")
    public void updateBank(@PathVariable("id") Long memberId, @RequestBody BankDetailsModel bankDetailsModel){
        if(memberId == null || bankDetailsModel == null){
            log.info("Member Id or bank object is null");
            return;
        }
        bankDetailService.editBankDetails(memberId, bankDetailsModel);
    }

    /**
     * Delete a Bank Object in DB by using the following memberId and bankId
     *
     * @param memberId
     */
    @DeleteMapping("members/{id}/delete/{bankId}")
    public void deleteBank(@PathVariable("id") Long memberId, @PathVariable("bankId") Long bankId){
        if(memberId == null || bankId == null){
            log.info("Member Id or bank Id is null");
            return;
        }
        bankDetailService.deleteBankDetails(memberId, bankId);
    }


}
