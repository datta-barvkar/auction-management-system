package org.scsmksn.npl.auction.common.constraint;

import lombok.Getter;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidatorContext;

public class GenericValidator {

    @Autowired
    @Getter
    private ProducerTemplate template;

    protected void addCustomConstraintViolation(final ConstraintValidatorContext context, final String messageKey) {

    }
}
