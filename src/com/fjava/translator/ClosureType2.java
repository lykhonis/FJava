package com.fjava.translator;

import static com.fjava.translator.TokenType.ASSIGN;
import static com.fjava.translator.TokenType.COMMA;
import static com.fjava.translator.TokenType.IDENTIFIER;
import static com.fjava.translator.TokenType.LPAREN;
import static com.fjava.translator.TokenType.RPAREN;
import static com.fjava.translator.TokenType.RT;

public class ClosureType2 extends Atom {

	public ClosureType2(TranslatorFactory factory) {
		super(factory);
	}

	@Override
	public String translate() {
		skipRequired();
		return format("final F2<$2, $4> $8");
	}

	@Override
	public boolean test() {
		return test(LPAREN, IDENTIFIER, COMMA, IDENTIFIER, RPAREN, ASSIGN, RT, IDENTIFIER);
	}
}
