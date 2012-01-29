package com.fjava.translator;

import static com.fjava.translator.TokenType.ASSIGN;
import static com.fjava.translator.TokenType.IDENTIFIER;
import static com.fjava.translator.TokenType.LPAREN;
import static com.fjava.translator.TokenType.RPAREN;
import static com.fjava.translator.TokenType.RT;
import static com.fjava.translator.TokenType.SEMICOLON;

public class ClosureNotInitialized extends Atom {

	public ClosureNotInitialized(TranslatorFactory factory) {
		super(factory);
	}

	@Override
	public String translate() {
		skipRequired();
		return format("F $5;");
	}

	@Override
	public boolean test() {
		return test(LPAREN, RPAREN, ASSIGN, RT, IDENTIFIER, SEMICOLON);
	}
}
