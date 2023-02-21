package br.com.dbserver.votacao.validator;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CpfOrCnpjValidator implements ConstraintValidator<CpfOuCnpj, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true; // valor nulo será validado em outra anotação, como @NotNull
		}

		CPFValidator cpfValidator = new CPFValidator();
		cpfValidator.initialize(null);
		if (cpfValidator.isValid(value, context)) {
			return true;
		}

		CNPJValidator cnpjValidator = new CNPJValidator();
		cnpjValidator.initialize(null);
		return cnpjValidator.isValid(value, context);
	}
}

