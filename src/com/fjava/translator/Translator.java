package com.fjava.translator;

public class Translator {

	final Tokenizer tokenizer;

	public Translator(String fjava) {
		tokenizer = new JavaTokenizer(fjava);
	}

	public String translate() {
		final StringBuilder stringBuilder = new StringBuilder();
		Token token = tokenizer.firstToken();

		if (token != null && token.type == TokenType.PACKAGE) {
			do {
				stringBuilder.append(token.image).append(token.type.postfix);
				if (token.type == TokenType.SEMICOLON) {
					token = tokenizer.nextToken();
					break;
				}
				token = tokenizer.nextToken();
			} while (token != null);
		}

		stringBuilder.append("import com.fjava.library.*;");

		if (token != null) {
			final TranslatorFactory factory = new TranslatorFactory(tokenizer);
			do {
				stringBuilder.append(factory.translate());
			} while (tokenizer.nextToken() != null);
		}

		return stringBuilder.toString();
	}
}
