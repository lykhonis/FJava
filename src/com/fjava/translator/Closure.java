package com.fjava.translator;

import static com.fjava.translator.TokenType.ASSIGN;
import static com.fjava.translator.TokenType.IDENTIFIER;
import static com.fjava.translator.TokenType.LBRACE;
import static com.fjava.translator.TokenType.LPAREN;
import static com.fjava.translator.TokenType.RBRACE;
import static com.fjava.translator.TokenType.RPAREN;
import static com.fjava.translator.TokenType.RT;

public class Closure extends Atom {

	public Closure(TranslatorFactory factory) {
		super(factory);
	}

	@Override
	public String translate() {
		final StringBuilder result = new StringBuilder(
				format("final F $1 = new F() { @Override public void invoke() { "));
		skipRequired();

		TokenType type = require();
		while (type != RBRACE) {
			result.append(get());
			type = require();
		}
		result.append("}};");

		return result.toString();
	}

	@Override
	public boolean test() {
		return test(IDENTIFIER, ASSIGN, LPAREN, RPAREN, ASSIGN, RT, LBRACE)
				|| test(IDENTIFIER, ASSIGN, ASSIGN, RT, LBRACE);
	}
}
