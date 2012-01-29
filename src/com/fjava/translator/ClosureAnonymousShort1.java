package com.fjava.translator;

import static com.fjava.translator.TokenType.ASSIGN;
import static com.fjava.translator.TokenType.IDENTIFIER;
import static com.fjava.translator.TokenType.LPAREN;
import static com.fjava.translator.TokenType.RPAREN;
import static com.fjava.translator.TokenType.RT;
import static com.fjava.translator.TokenType.SEMICOLON;
import static com.fjava.translator.TokenType.COMMA;

public class ClosureAnonymousShort1 extends Atom {

	public ClosureAnonymousShort1(TranslatorFactory factory) {
		super(factory);
	}

	@Override
	public String translate() {
		final StringBuilder result = new StringBuilder(format("new F1<$2>() { @Override public $2 invoke() { return "));
		skipRequired();

		int parenCounter = 0;
		TokenType type = require();
		while (type != SEMICOLON && type != COMMA) {
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
		else if (type == COMMA)
			result.append(',');

		return result.toString();
	}

	@Override
	public boolean test() {
		return test(LPAREN, IDENTIFIER, RPAREN, ASSIGN, RT);
	}
}
