package com.fjava.translator;

import static com.fjava.translator.TokenType.ASSIGN;
import static com.fjava.translator.TokenType.COMMA;
import static com.fjava.translator.TokenType.IDENTIFIER;
import static com.fjava.translator.TokenType.LBRACE;
import static com.fjava.translator.TokenType.LPAREN;
import static com.fjava.translator.TokenType.RBRACE;
import static com.fjava.translator.TokenType.RPAREN;
import static com.fjava.translator.TokenType.RT;

public class ClosureAnonymous2 extends Atom {

	public ClosureAnonymous2(TranslatorFactory factory) {
		super(factory);
	}

	@Override
	public String translate() {
		StringBuilder result = new StringBuilder(format("new F2<$2, $4>() { @Override public $2 invoke(final $4 $5) { "));
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
		return test(LPAREN, IDENTIFIER, COMMA, IDENTIFIER, IDENTIFIER, RPAREN, ASSIGN, RT, LBRACE);
	}
}
