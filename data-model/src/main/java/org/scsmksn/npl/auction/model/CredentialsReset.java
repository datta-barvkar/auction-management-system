package org.scsmksn.npl.auction.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CredentialsReset {

    private String username;

    private String oldPassword;

    private String newPassword;

    private String repeatPassword;
}
