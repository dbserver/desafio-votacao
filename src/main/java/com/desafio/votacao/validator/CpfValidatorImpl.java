package com.desafio.votacao.validator;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfValidatorImpl implements ConstraintValidator<CpfValidator, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true; // valor nulo será validado em outra anotação, como @NotNull
		}

		CPFValidator cpfValidator = new CPFValidator();
		cpfValidator.initialize(null);
		return cpfValidator.isValid(value, context);
	}
}
