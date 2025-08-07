package com.spartaschedulerdevelop.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;

public class AtLeastOneNotNullValidator implements ConstraintValidator<AtLeastOneNotNull, Object> {
    private String[] fields;

    @Override
    public void initialize(AtLeastOneNotNull annotation) {
        this.fields = annotation.fields();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) return false;

        try {
            for (String field : fields) {
                Field f = value.getClass().getDeclaredField(field);
                f.setAccessible(true);
                Object fieldValue = f.get(value);

                if (fieldValue != null) {
                    if (fieldValue instanceof String && !((String) fieldValue).trim().isEmpty()) {
                        return true;
                    } else if (!(fieldValue instanceof String)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }
}
