package com.scholar.beanutils;

import java.beans.FeatureDescriptor;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class MyBeanUtils {

	public static void copyNonNullProperties(Object source, Object target) {
		BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
	}
	
	public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors())
            .map(FeatureDescriptor::getName)
            .filter(propertyName -> {
            	return isNullOrEmpty(wrappedSource.getPropertyValue(propertyName));
            })
            .toArray(String[]::new);
    }
	
	private static Boolean isNullOrEmpty(Object object) {
    	Boolean isEmpty = false;
    	    	    	
    	if(object instanceof Set<?> || object instanceof List<?>)
    		isEmpty = ((Collection<?>) object).isEmpty();
    	
    	return object == null || isEmpty;
	}
}
