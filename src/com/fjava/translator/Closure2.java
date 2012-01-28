package com.fjava.translator;

import static com.fjava.translator.TokenType.ASSIGN;
import static com.fjava.translator.TokenType.COMMA;
import static com.fjava.translator.TokenType.IDENTIFIER;
import static com.fjava.translator.TokenType.LBRACE;
import static com.fjava.translator.TokenType.LPAREN;
import static com.fjava.translator.TokenType.RBRACE;
import static com.fjava.translator.TokenType.RPAREN;
import static com.fjava.translator.TokenType.RT;

public class Closure2 extends Atom {

	public Closure2(TranslatorFactory factory) {
		super(factory);
	}

	@Override
	public String translate() {
		final StringBuilder result = new StringBuilder(
				format("final F2<$4, $6> $1 = new F2<$4, $6>() { @Override public $4 invoke(final $6 $7) { "));
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
		return test(IDENTIFIER, ASSIGN, LPAREN, IDENTIFIER, COMMA, IDENTIFIER, IDENTIFIER, RPAREN, ASSIGN, RT, LBRACE);
	}
}
