package com.fjava.translator;

class Token {

	public final String image;
	public final Token next;
	public final TokenType type;

	Token(String image, TokenType type, Token next) {
		this.image = image;
		this.next = next;
		this.type = type;
	}
}
