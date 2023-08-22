package org.scsmksn.npl.auction.common.controllers;

import org.scsmksn.npl.auction.common.exceptions.AuctionRestErrorException;
import org.scsmksn.npl.auction.common.template.RestCamelRequestTemplate;
import org.scsmksn.npl.auction.common.template.WebCamelRequestTemplate;
import org.scsmksn.npl.auction.model.RestError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

public class GenericRestController {

    @Autowired
    private RestCamelRequestTemplate template;

    protected RestCamelRequestTemplate getTemplate() {
        return template;
    }

    protected RestError getRestError(final HttpStatus httpStatus, final List<String> errorMessages) {
        final RestError restError = new RestError();
        restError.setHttpErrorCode(httpStatus.value());
        restError.setHttpErrorMessage(httpStatus.getReasonPhrase());
        restError.setErrorMessages(errorMessages);
        return restError;
    }

    protected ResponseEntity<RestError> convertToResponseEntity(Exception ex) {
        if (ex instanceof AuctionRestErrorException) {
            final HttpStatus status = ((AuctionRestErrorException) ex).getStatusCode();
            return ResponseEntity.status(status).body(getRestError(status, Collections.singletonList(ex.getMessage())));
        }
        return ResponseEntity.internalServerError().body(getRestError(HttpStatus.INTERNAL_SERVER_ERROR
                , Collections.singletonList(ex.getMessage())));
    }

    protected void checkValidationResult(final BindingResult result) {
        if (result.hasFieldErrors()) {
            final List<String> fieldErrors = result.getFieldErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
            throw new AuctionRestErrorException(fieldErrors, BAD_REQUEST, BAD_REQUEST.getReasonPhrase());
        }
        if (result.hasGlobalErrors()) {
            final List<String> globalErrors = result.getGlobalErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
            throw new AuctionRestErrorException(globalErrors, UNPROCESSABLE_ENTITY
                    , UNPROCESSABLE_ENTITY.getReasonPhrase());
        }
    }
}
