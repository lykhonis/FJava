package com.fjava.translator;

import static com.fjava.translator.TokenType.ASSIGN;
import static com.fjava.translator.TokenType.IDENTIFIER;
import static com.fjava.translator.TokenType.LPAREN;
import static com.fjava.translator.TokenType.RPAREN;
import static com.fjava.translator.TokenType.RT;
import static com.fjava.translator.TokenType.SEMICOLON;

public class ClosureShort1 extends Atom {

	public ClosureShort1(TranslatorFactory factory) {
		super(factory);
	}

	@Override
	public String translate() {
		final StringBuilder result = new StringBuilder(
				format("final F1<$4> $1 = new F1<$4>() { @Override public $4 invoke() { return "));
		skipRequired();

		TokenType type = require();
		while (type != SEMICOLON) {
			result.append(get());
			type = require();
		}
		result.append(";}};");

		return result.toString();
	}

	@Override
	public boolean test() {
		return test(IDENTIFIER, ASSIGN, LPAREN, IDENTIFIER, RPAREN, ASSIGN, RT);
	}
}
