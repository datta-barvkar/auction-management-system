package org.scsmksn.npl.auction.web.controllers;

import org.scsmksn.npl.auction.common.exceptions.AuctionRestErrorException;
import org.scsmksn.npl.auction.common.exceptions.AuctionWebErrorException;
import org.scsmksn.npl.auction.model.RestError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static org.scsmksn.npl.auction.common.utils.AuctionConstants.DEFAULT_ERROR_VIEW;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.ERROR_MESSAGE;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.REQUEST_URL;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.RESPONSE_CODE;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.RESPONSE_TEXT;

@ControllerAdvice
public class AuctionExceptionHandler {

    @ExceptionHandler({AuctionWebErrorException.class, HttpClientErrorException.class})
    public String handleWebErrorException(final HttpServletRequest request, final HttpStatusCodeException ex
            , final Model model) {
        model.addAllAttributes(getModelMap(ex.getStatusCode(), ex.getMessage(), request.getRequestURL().toString()));
        return DEFAULT_ERROR_VIEW;
    }

    @ExceptionHandler(AuctionRestErrorException.class)
    public ResponseEntity<RestError> handleRestErrorException(final AuctionRestErrorException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(getRestError(ex));
    }

    private Map<String, String> getModelMap(final HttpStatus status, final String message, final String requestURL) {
        final HashMap<String, String> modelMap = new HashMap<>();
        modelMap.put(RESPONSE_CODE, String.valueOf(status.value()));
        modelMap.put(RESPONSE_TEXT, status.getReasonPhrase());
        modelMap.put(ERROR_MESSAGE, message);
        modelMap.put(REQUEST_URL, requestURL);
        return modelMap;
    }

    private RestError getRestError(final AuctionRestErrorException ex) {
        final RestError restError = new RestError();
        final HttpStatus statusCode = ex.getStatusCode();
        restError.setHttpErrorCode(statusCode.value());
        restError.setHttpErrorMessage(statusCode.getReasonPhrase());
        restError.setErrorMessages(ex.getErrorMessages());
        return restError;
    }
}
