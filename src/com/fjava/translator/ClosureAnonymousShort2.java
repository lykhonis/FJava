package com.fjava.translator;

import static com.fjava.translator.TokenType.*;

public class ClosureAnonymousShort2 extends Atom {

	public ClosureAnonymousShort2(TranslatorFactory factory) {
		super(factory);
	}

	@Override
	public String translate() {
		final StringBuilder result = new StringBuilder(
				format("new F2<$2, $4>() { @Override public $2 invoke(final $4 $5) { return "));
		skipRequired();

		int parenCounter = 0;
		TokenType type = require();
		while (type != SEMICOLON) {
			if (type == LPAREN)
				parenCounter++;
			else if (type == RPAREN && --parenCounter < 0)
				break;

			result.append(get());
			type = require();
		}
		result.append(";}}");

		if (type == RPAREN)
			result.append(')');

		return result.toString();
	}

	@Override
	public boolean test() {
		return test(LPAREN, IDENTIFIER, COMMA, IDENTIFIER, IDENTIFIER, RPAREN, ASSIGN, RT);
	}
}
