package org.scsmksn.npl.auction.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RestError {

    private int httpErrorCode;
    private String httpErrorMessage;
    private List<String> errorMessages;
}
