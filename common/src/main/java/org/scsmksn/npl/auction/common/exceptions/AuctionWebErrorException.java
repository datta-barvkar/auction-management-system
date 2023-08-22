package org.scsmksn.npl.auction.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

import java.nio.charset.Charset;

public class AuctionWebErrorException extends HttpStatusCodeException {

    public AuctionWebErrorException(final String message, final HttpStatus statusCode, final String statusText) {
        super(message, statusCode, statusText, null, null, Charset.defaultCharset());
    }
}
