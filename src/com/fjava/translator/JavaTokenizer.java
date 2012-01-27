package com.fjava.translator;

import java.util.regex.Matcher;

public class JavaTokenizer implements Tokenizer {

	private final Token first;
	private Token current;

	public JavaTokenizer(String image) {
		first = parse(image);
		current = first;
	}

	@Override
	public Token firstToken() {
		current = first;
		return current;
	}

	@Override
	public Token nextToken() {
		if (current.next != null)
			return current = current.next;
		return null;
	}

	@Override
	public Token getToken(int index) {
		Token result = current;
		for (int i = 0; i < index && result != null; i++)
			result = result.next;
		return result;
	}

	@Override
	public void skip(int count) {
		int n = count;
		while (n-- > 0 && current != null)
			current = current.next;
	}

	private static Token parse(String image) {
		int position = 0;
		final int length = image.length();

		while (position < length) {
			final char character = image.charAt(position);
			if (character == ' ' || character == '\r' || character == '\t' || character == '\n' || character == '\f') {
				position++;
			} else
				break;
		}

		final String tmp = image.substring(position);

		for (TokenType type : TokenType.values()) {
			Matcher matcher = type.r.matcher(tmp);
			if (matcher.find()) {
				final String tokenImage = matcher.group(1);
				return new Token(tokenImage, type, parse(tmp.substring(tokenImage.length())));
			}
		}

		return null;
	}
}
