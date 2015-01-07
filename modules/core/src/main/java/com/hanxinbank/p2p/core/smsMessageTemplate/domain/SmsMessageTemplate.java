package com.hanxinbank.p2p.core.smsMessageTemplate.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "SMS_MESSAGE_TEMPLATES")
public class SmsMessageTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SMS_MESSAGE_TEMPLATE")
    @SequenceGenerator(name = "SEQ_SMS_MESSAGE_TEMPLATE", sequenceName = "SEQ_SMS_MESSAGE_TEMPLATE", allocationSize = 1)
    private Long id;

    @Column(name = "TYPE")
    private SmsMessageType type;

    @Column(name = "MESSAGE_TEMPLATE")
    private String messageTemplate;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updateAt;

    public static Builder aSmsMessageTemplate() {
        return new Builder();
    }

    public static class Builder {
        private SmsMessageType type;
        private String messageTemplate;

        public Builder withTypeCode(SmsMessageType type) {
            this.type = type;
            return this;
        }

        public Builder withMessageTemplate(String messageTemplate) {
            this.messageTemplate = messageTemplate;
            return this;
        }

        public SmsMessageTemplate build() {
            SmsMessageTemplate smsMessageTemplate = new SmsMessageTemplate();
            smsMessageTemplate.type = type;
            smsMessageTemplate.messageTemplate = messageTemplate;
            return smsMessageTemplate;
        }
    }
}
