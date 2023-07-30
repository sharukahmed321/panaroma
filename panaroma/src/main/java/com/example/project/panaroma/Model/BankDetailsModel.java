package com.example.project.panaroma.Model;

import lombok.Data;

@Data
public class BankDetailsModel {
    private Long bankId;
    private String accountNumber;
    private String bankName;
    private String bankAccountCountry;
    private String bankAccountHolderName;
    private MemberModel member;
}
