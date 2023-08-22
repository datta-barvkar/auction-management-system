package org.scsmksn.npl.auction.common.validators;

import org.scsmksn.npl.auction.model.Entity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Collection;

@Component
public class CollectionValidator implements Validator {

    private final Validator validator;

    public CollectionValidator(final Validator validator) {
        this.validator = validator;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Collection.class.isAssignableFrom(clazz);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void validate(final Object target, final Errors errors) {
        final Collection<Entity> collection = (Collection<Entity>) target;
        collection.stream().filter(o -> validator.supports(o.getClass())).forEach(o -> {
            final BeanPropertyBindingResult result = new BeanPropertyBindingResult(o, o.getObjectName());
            ValidationUtils.invokeValidator(validator, o, result);
            result.getAllErrors().forEach(fieldError -> errors.reject(errors.getObjectName() + "." + "fieldErrors", fieldError.toString()));
            result.getGlobalErrors().forEach(globalError -> errors.reject(errors.getObjectName() + "." + "globalErrors", globalError.toString()));
        });
    }
}
