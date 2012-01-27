package com.fjava.translator;

import static com.fjava.translator.TokenType.ASSIGN;
import static com.fjava.translator.TokenType.IDENTIFIER;
import static com.fjava.translator.TokenType.LPAREN;
import static com.fjava.translator.TokenType.RPAREN;
import static com.fjava.translator.TokenType.RT;

public class ClosureType extends Atom {

	public ClosureType(TranslatorFactory factory) {
		super(factory);
	}

	@Override
	public String translate() {
		skipRequired();
		return format("final F $5");
	}

	@Override
	public boolean test() {
		return test(LPAREN, RPAREN, ASSIGN, RT, IDENTIFIER);
	}
}
