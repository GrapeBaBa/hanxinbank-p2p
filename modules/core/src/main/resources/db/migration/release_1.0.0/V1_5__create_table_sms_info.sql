CREATE SEQUENCE SEQ_SMS_INFO START WITH 1 INCREMENT BY 1 CACHE 10;

CREATE TABLE SMS_INFOS (
  ID                      NUMBER(19),
  EXPECTED_SEND_TIME      TIMESTAMP,
  MOBILE                  VARCHAR2(13),
  TEMPLATE_ID             NUMBER(19),
  CONTENT                 VARCHAR2(4000),
  FETCH_TIME              TIMESTAMP,
  SEND_TIME               TIMESTAMP,
  SEND_STATUS             VARCHAR2(20),
  RESULT_CODE             VARCHAR2(20),
  CREATED_AT              TIMESTAMP DEFAULT SYSDATE,
  UPDATED_AT              TIMESTAMP DEFAULT SYSDATE,
  VERSION                 NUMBER(19),
  CONSTRAINT PK_SMS_INFOS_ID PRIMARY KEY (ID),
  CONSTRAINT FK_SMS_INFOS_TMPL_ID FOREIGN KEY(TEMPLATE_ID) REFERENCES SMS_MESSAGE_TEMPLATES(ID),
);
COMMENT ON TABLE SMS_INFOS                                        IS '短信信息表';
COMMENT ON COLUMN SMS_INFOS.ID			                              IS '短信ID';
COMMENT ON COLUMN SMS_INFOS.EXPECTED_SEND_TIME	                  IS '期望发送的时间';
COMMENT ON COLUMN SMS_INFOS.MOBILE	                              IS '手机号';
COMMENT ON COLUMN SMS_INFOS.TEMPLATE_ID	                          IS '短信模板ID';
COMMENT ON COLUMN SMS_INFOS.CONTENT	                              IS '短信内容';
COMMENT ON COLUMN SMS_INFOS.FETCH_TIME	                          IS 'job发送查询到的时间';
COMMENT ON COLUMN SMS_INFOS.SEND_TIME	                            IS '实际发送的时间';
COMMENT ON COLUMN SMS_INFOS.SEND_STATUS	                          IS '状态';
COMMENT ON COLUMN SMS_INFOS.RESULT_CODE	                          IS '返回码';
COMMENT ON COLUMN SMS_INFOS.CREATED_AT	                          IS '创建时间';
COMMENT ON COLUMN SMS_INFOS.UPDATED_AT	                          IS '更新时间';
COMMENT ON COLUMN SMS_INFOS.VERSION	                              IS '版本号';