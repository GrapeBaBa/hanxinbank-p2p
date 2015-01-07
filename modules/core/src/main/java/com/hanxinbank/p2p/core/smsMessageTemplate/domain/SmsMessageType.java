package com.hanxinbank.p2p.core.smsMessageTemplate.domain;

public enum SmsMessageType {
    CreateDealPassword,
    ChangeDealPassword,
    FindDealPassword,
    CreateUser,
    FindPassword,
    Withdraw,
    CouponForRegister,
    CouponForIdentityAuthentication,
    ModifyPhone,
    AutoInvestmentMainMessageTemplate,
    AutoInvestmentGroupMessageTemplate,
    InvestmentFailure,
    InvestmentSuccess,
    ManualWithdrawFailed,
    ManualWithdrawRejected,
    CommitManualWithDrawNotification,
    ManualWithDrawSuccess,
    RechargeSuccess,
    ReceiveRepaymentSuccess,
    PrepaymentSuccess,
    CouponForInvitingFriends,
    VerifyNewPhone,
    VerifyOldPhone,
    NOTICE_CREDITOR_AFTER_SUCCESSFUL_TRANSFER,
    SEND_VERIFICATION_MESSAGE;
}
