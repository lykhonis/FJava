package com.fjava.translator;

import static com.fjava.translator.TokenType.ASSIGN;
import static com.fjava.translator.TokenType.IDENTIFIER;
import static com.fjava.translator.TokenType.LAZY;
import static com.fjava.translator.TokenType.LBRACE;
import static com.fjava.translator.TokenType.RBRACE;
import static com.fjava.translator.TokenType.SEMICOLON;

public class Lazy extends Atom {

	public Lazy(TranslatorFactory factory) {
		super(factory);
	}

	@Override
	public String translate() {
		StringBuilder result = new StringBuilder(
				format("final Lazy<$2> $3 = new Lazy<$2>(new F1<$2>() { @Override public $2 invoke() "));
		skipRequired();

		TokenType type = require();
		if (type == LBRACE) {
			do {
				result.append(get());
				type = require();
			} while (type != RBRACE);
			result.append("}});");
		} else {
			result.append("{return ");
			do {
				result.append(get());
				type = require();
			} while (type != SEMICOLON);
			result.append(";}});");
		}

		return result.toString();
	}

	@Override
	public boolean test() {
		return test(LAZY, IDENTIFIER, IDENTIFIER, ASSIGN);
	}
}
