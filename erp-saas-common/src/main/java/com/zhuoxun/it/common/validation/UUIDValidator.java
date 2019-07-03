package com.zhuoxun.it.common.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.zhuoxun.it.common.utils.ValidatorUtils;

/**
 * 手机校验拦截
 * 
 * @author liwen
 */
public class UUIDValidator implements ConstraintValidator<UUID, String> {

    private boolean required = false;

    @Override
    public void initialize(UUID constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (required == false) {
            return ValidatorUtils.isValidUUID(value);
        } else {
            if (StringUtils.isEmpty(value)) {
                return true;
            }
            return ValidatorUtils.isValidUUID(value);
        }
    }
}
