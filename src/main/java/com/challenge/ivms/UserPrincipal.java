package com.challenge.ivms;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class UserPrincipal {

    public static final String EOTP_AUTH_USER_TYPE = "REGISTERED_USER";
    public static final String OKPASS_AUTH_USER_TYPE = "CUSTOMER";

    private final String authDeviceId;
    private final String authUserId;
    private final String authUserType;
    private final Integer authUserLevel;
    private final String siebelUserRelationId;
    private final String siebelCustomerRelationId;
    private final String edoKlid;
    private final String edoAgreementId;
    private final String edoUserId;
    @Setter
    @ToString.Exclude
    private String token;

    @JsonCreator
    public UserPrincipal(@JsonProperty(value = "siebelCustomerRelationId") String siebelCustomerRelationId,
                         @JsonProperty(value = "siebelUserRelationId") String siebelUserRelationId,
                         @JsonProperty(value = "edoKlid") String edoKlid,
                         @JsonProperty(value = "edoAgreementId") String edoAgreementId,
                         @JsonProperty(value = "edoUserId") String edoUserId,
                         @JsonProperty(value="authDeviceId") String authDeviceId,
                         @JsonProperty(value="authUserId", required = true) String authUserId,
                         @JsonProperty(value="authUserType") String authUserType,
                         @JsonProperty(value="authUserLevel") Integer authUserLevel) {

        this.siebelCustomerRelationId = siebelCustomerRelationId;
        this.siebelUserRelationId = siebelUserRelationId;
        this.edoKlid = edoKlid;
        this.edoAgreementId = edoAgreementId;
        this.edoUserId = edoUserId;
        this.authDeviceId = authDeviceId;
        this.authUserId = authUserId;
        this.authUserType = authUserType;
        this.authUserLevel = authUserLevel;
    }

}
