package com.fjava.translator;

import static com.fjava.translator.TokenType.ASSIGN;
import static com.fjava.translator.TokenType.IDENTIFIER;
import static com.fjava.translator.TokenType.LPAREN;
import static com.fjava.translator.TokenType.RPAREN;
import static com.fjava.translator.TokenType.RT;

public class ClosureType1 extends Atom {

	public ClosureType1(TranslatorFactory factory) {
		super(factory);
	}

	@Override
	public String translate() {
		skipRequired();
		return format("final F1<$2> $6");
	}

	@Override
	public boolean test() {
		return test(LPAREN, IDENTIFIER, ASSIGN, RT, RPAREN, IDENTIFIER);
	}
}
