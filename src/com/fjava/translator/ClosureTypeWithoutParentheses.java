package com.fjava.translator;

import static com.fjava.translator.TokenType.ASSIGN;
import static com.fjava.translator.TokenType.IDENTIFIER;
import static com.fjava.translator.TokenType.RT;

public class ClosureTypeWithoutParentheses extends Atom {

	public ClosureTypeWithoutParentheses(TranslatorFactory factory) {
		super(factory);
	}

	@Override
	public String translate() {
		skipRequired();
		return format("final F $3");
	}

	@Override
	public boolean test() {
		return test(ASSIGN, RT, IDENTIFIER);
	}
}
