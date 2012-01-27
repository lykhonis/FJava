package com.fjava.translator;

import static com.fjava.translator.TokenType.ASSIGN;
import static com.fjava.translator.TokenType.LBRACE;
import static com.fjava.translator.TokenType.LPAREN;
import static com.fjava.translator.TokenType.RBRACE;
import static com.fjava.translator.TokenType.RPAREN;
import static com.fjava.translator.TokenType.RT;

public class ClosureAnonymous extends Atom {

	public ClosureAnonymous(TranslatorFactory factory) {
		super(factory);
	}

	@Override
	public String translate() {
		StringBuilder result = new StringBuilder(format("new F() { @Override public void invoke() { "));
		skipRequired();

		TokenType type = require();
		while (type != RBRACE) {
			result.append(get());
			type = require();
		}
		result.append("}}");

		return result.toString();
	}

	@Override
	public boolean test() {
		return test(LPAREN, RPAREN, ASSIGN, RT, LBRACE) || test(ASSIGN, RT, LBRACE);
	}
}
