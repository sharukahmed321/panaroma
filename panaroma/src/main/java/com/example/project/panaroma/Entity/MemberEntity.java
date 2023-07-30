package com.example.project.panaroma.Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "members")
public class MemberEntity {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "kyc_level")
    private String kycLevel;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "mobile_number_verified")
    private boolean mobileNumberVerified;

    @Column(name = "created_date")
    private String createdDate;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<BankDetailsEntity> banks;

}
