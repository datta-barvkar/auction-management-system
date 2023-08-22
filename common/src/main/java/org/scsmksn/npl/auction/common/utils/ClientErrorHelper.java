package org.scsmksn.npl.auction.common.utils;

import org.scsmksn.npl.auction.common.exceptions.AuctionRestErrorException;
import org.scsmksn.npl.auction.common.exceptions.AuctionWebErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.nio.charset.Charset;
import java.util.Collections;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Component
public class ClientErrorHelper {

    public static HttpClientErrorException createUnauthorized(final String message) {
        return new HttpClientErrorException(message, UNAUTHORIZED, UNAUTHORIZED.getReasonPhrase(), null, null, Charset.defaultCharset());
    }

    public static HttpClientErrorException createForbidden(final String message) {
        return new HttpClientErrorException(message, FORBIDDEN, FORBIDDEN.getReasonPhrase(), null, null, Charset.defaultCharset());
    }

    public static HttpClientErrorException createNotFound(final String message) {
        return new HttpClientErrorException(message, NOT_FOUND, NOT_FOUND.getReasonPhrase(), null, null, Charset.defaultCharset());
    }

    public static HttpClientErrorException createUnprocessableEntity(final String message) {
        return new HttpClientErrorException(message, UNPROCESSABLE_ENTITY, UNPROCESSABLE_ENTITY.getReasonPhrase(), null, null, Charset.defaultCharset());
    }

    public static HttpClientErrorException createBadRequest(final String message) {
        return new HttpClientErrorException(message, BAD_REQUEST, BAD_REQUEST.getReasonPhrase(), null, null, Charset.defaultCharset());
    }

    public static AuctionRestErrorException convertToAuctionRestErrorException(final HttpClientErrorException ex) {
        final HttpStatus statusCode = ex.getStatusCode();
        return new AuctionRestErrorException(Collections.singletonList(ex.getMessage()), statusCode, statusCode.getReasonPhrase());
    }

    public static AuctionWebErrorException convertToAuctionWebErrorException(final HttpClientErrorException ex) {
        final HttpStatus statusCode = ex.getStatusCode();
        return new AuctionWebErrorException(ex.getMessage(), statusCode, statusCode.getReasonPhrase());
    }
}
