package com.example.project.panaroma.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "banks")
public class BankDetailsEntity {

    @Id
    @Column(name = "bank_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bankId;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "bank_account_country")
    private String bankAccountCountry;

    @Column(name = "bank_account_holder_name")
    private String bankAccountHolderName;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity member;
}
