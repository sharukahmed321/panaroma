package com.example.project.panaroma.Model;

import lombok.Data;
import java.util.List;

@Data
public class MemberModel {
    private Long memberId;
    private String memberName;
    private String kycLevel;
    private String mobileNumber;
    private boolean mobileNumberVerified;
    private String createdDate;
    private List<BankDetailsModel> banks;

}
