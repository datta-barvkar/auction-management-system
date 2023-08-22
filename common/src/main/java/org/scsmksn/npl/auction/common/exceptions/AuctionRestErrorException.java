package org.scsmksn.npl.auction.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

import java.nio.charset.Charset;
import java.util.List;

public class AuctionRestErrorException extends HttpStatusCodeException {

    private List<String> errorMessages;

    public AuctionRestErrorException(final List<String> messages, final HttpStatus statusCode, final String statusText) {
        super(statusCode, statusText, null, null, Charset.defaultCharset());
        errorMessages = messages;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }
}
