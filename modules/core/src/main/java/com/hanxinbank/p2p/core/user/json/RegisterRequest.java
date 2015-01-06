package com.hanxinbank.p2p.core.user.json;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

public class RegisterRequest implements Serializable {
    private String username;

    private String password;

    private String termsAndConditions;

    private String mobileVerification;

    private String mobile;

    private String captcha;

    public RegisterRequest() {

    }

    public static class Builder {
        private String username;

        private String password;

        private String termsAndConditions;

        private String mobileVerification;

        private String mobile;

        private String captcha;

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withTermAndConditions(String termsAndConditions) {
            this.termsAndConditions = termsAndConditions;
            return this;
        }

        public Builder withMobileVerification(String mobileVerification) {
            this.mobileVerification = mobileVerification;
            return this;
        }

        public Builder withMobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

        public Builder withCaptcha(String captcha) {
            this.captcha = captcha;
            return this;
        }

        public RegisterRequest build() {
            RegisterRequest registerRequest = new RegisterRequest();
            registerRequest.captcha = captcha;
            registerRequest.mobile = mobile;
            registerRequest.mobileVerification = mobileVerification;
            registerRequest.password = password;
            registerRequest.termsAndConditions = termsAndConditions;
            registerRequest.username = username;
            return registerRequest;
        }
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(RegisterRequest.class)
                .add("username", username)
                .add("mobile", mobile)
                .add("mobileVerification", mobileVerification)
                .add("termsAndConditions", termsAndConditions)
                .add("captcha", captcha)
                .toString();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getTermsAndConditions() {
        return termsAndConditions;
    }

    public String getMobileVerification() {
        return mobileVerification;
    }

    public String getMobile() {
        return mobile;
    }

    public String getCaptcha() {
        return captcha;
    }
}
