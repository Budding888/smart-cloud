package com.smart.common.util;

import cn.hutool.core.convert.Convert;
import com.google.common.collect.Lists;
import com.smart.common.result.ParameterInvalidItem;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author LErry.li
 * Description:
 * 类型转换工具类
 * Date: 2019/7/23 15:39
 */
public class ConvertUtil extends Convert {

    public static List<ParameterInvalidItem> convertCVSetToParameterInvalidItemList(Set<ConstraintViolation<?>> cvset) {
        if (CollectionUtils.isEmpty(cvset)) {
            return Collections.emptyList();
        }
        char ch = '.';
        List<ParameterInvalidItem> parameterInvalidItemList = Lists.newArrayList();
        for (ConstraintViolation<?> cv : cvset) {
            ParameterInvalidItem parameterInvalidItem = new ParameterInvalidItem();
            String propertyPath = cv.getPropertyPath().toString();
            if (propertyPath.indexOf(ch) != -1) {
                String[] propertyPathArr = propertyPath.split("\\.");
                parameterInvalidItem.setFieldName(propertyPathArr[propertyPathArr.length - 1]);
            } else {
                parameterInvalidItem.setFieldName(propertyPath);
            }
            parameterInvalidItem.setMessage(cv.getMessage());
            parameterInvalidItemList.add(parameterInvalidItem);
        }

        return parameterInvalidItemList;
    }

    public static List<ParameterInvalidItem> convertBindingResultToMapParameterInvalidItemList(BindingResult bindingResult) {
        if (bindingResult == null) {
            return Collections.emptyList();
        }

        List<ParameterInvalidItem> parameterInvalidItemList = Lists.newArrayList();

        List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
        for (FieldError fieldError : fieldErrorList) {
            ParameterInvalidItem parameterInvalidItem = new ParameterInvalidItem();
            parameterInvalidItem.setFieldName(fieldError.getField());
            parameterInvalidItem.setMessage(fieldError.getDefaultMessage());
            parameterInvalidItemList.add(parameterInvalidItem);
        }

        return parameterInvalidItemList;
    }
}
