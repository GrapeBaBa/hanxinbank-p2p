package com.hanxinbank.p2p.core.smsInfo.domain;

import com.hanxinbank.p2p.core.smsMessageTemplate.domain.SmsMessageTemplate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "SMS_INFOS")
public class SmsInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SMS_INFO")
    @SequenceGenerator(name = "SEQ_SMS_INFO", sequenceName = "SEQ_SMS_INFO", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "EXPECTED_SEND_TIME")
    private LocalDateTime expectedSendTime;

    @Column(name = "MOBILE")
    private String mobile;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEMPLATE_ID", nullable = false)
    private SmsMessageTemplate template;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "FETCH_TIME")
    private LocalDateTime fetchTime;

    @Column(name = "SEND_TIME")
    private LocalDateTime sendTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "SEND_STATUS")
    private SmsStatus sendStatus;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @Column(name = "RESULT_CODE")
    private SmsResultCode resultCode;

    @Version
    private long version;

    public SmsInfo() {
    }

}


