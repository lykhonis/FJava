package com.fjava.translator;

import static com.fjava.translator.TokenType.ASSIGN;
import static com.fjava.translator.TokenType.IDENTIFIER;
import static com.fjava.translator.TokenType.LBRACE;
import static com.fjava.translator.TokenType.LPAREN;
import static com.fjava.translator.TokenType.RBRACE;
import static com.fjava.translator.TokenType.RPAREN;
import static com.fjava.translator.TokenType.RT;

public class ClosureAnonymous1 extends Atom {

	public ClosureAnonymous1(TranslatorFactory factory) {
		super(factory);
	}

	@Override
	public String translate() {
		StringBuilder result = new StringBuilder(format("new F1<$2>() { @Override public $2 invoke() { "));
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
		return test(LPAREN, IDENTIFIER, RPAREN, ASSIGN, RT, LBRACE);
	}
}
