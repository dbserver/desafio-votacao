package br.com.dbserver.votacao.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CpfOrCnpjValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CpfOuCnpj {
	String message() default "CPF ou CNPJ inválido";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}

