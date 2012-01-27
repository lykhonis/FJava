package com.fjava.translator;

import java.util.ArrayList;
import java.util.List;

abstract class Atom implements Translatable {

	private final TranslatorFactory factory;
	private List<String> tokens = new ArrayList<String>();

	public Atom(TranslatorFactory factory) {
		this.factory = factory;
	}

	protected boolean test(TokenType... types) {
		if (types == null || types.length == 0)
			throw new FJavaTranslatingException("Invalid tokens for " + getClass().getSimpleName());
		int i = 0;
		tokens.clear();
		for (TokenType type : types) {
			final Token token = factory.tokenizer.getToken(i++);
			if (token == null || token.type != type) {
				tokens.clear();
				return false;
			}
			tokens.add(token.image);
		}
		return true;
	}

	protected String format(String format) {
		String result = format;
		int i = 1;
		for (String token : tokens)
			result = result.replaceAll("\\$" + i++, token);
		return result;
	}

	protected String get() {
		return factory.translate();
	}

	protected void skipRequired() {
		factory.tokenizer.skip(tokens.size() - 1);
	}

	protected TokenType require() {
		final Token token = factory.tokenizer.nextToken();

		if (token == null)
			throw new FJavaTranslatingException("Expecting something but found EOF.");

		return token.type;
	}
}
