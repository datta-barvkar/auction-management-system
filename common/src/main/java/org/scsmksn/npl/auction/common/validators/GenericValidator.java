package org.scsmksn.npl.auction.common.validators;

import org.scsmksn.npl.auction.common.template.RestCamelRequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Validator;

public abstract class GenericValidator implements Validator {

    @Autowired
    private RestCamelRequestTemplate template;

    public RestCamelRequestTemplate getTemplate() {
        return template;
    }
}
