package org.scsmksn.npl.auction.common.validators.advice;

import org.scsmksn.npl.auction.common.validators.CollectionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.Collection;

@ControllerAdvice
public class ValidatorAdvice {

    @Autowired
    private CollectionValidator validator;

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        if (binder.getTarget() instanceof Collection) {
            binder.addValidators(validator);
        }
    }
}
