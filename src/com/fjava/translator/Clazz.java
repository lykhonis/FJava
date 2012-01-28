package com.fjava.translator;

import static com.fjava.translator.TokenType.CLASS;
import static com.fjava.translator.TokenType.COMMA;
import static com.fjava.translator.TokenType.IDENTIFIER;
import static com.fjava.translator.TokenType.LPAREN;
import static com.fjava.translator.TokenType.PRIVATE;
import static com.fjava.translator.TokenType.PROTECTED;
import static com.fjava.translator.TokenType.PUBLIC;
import static com.fjava.translator.TokenType.RPAREN;

import java.util.ArrayList;
import java.util.List;

public class Clazz extends Atom {

	private static class Field {

		public String visibility;
		public String type;
		public String name;
	}

	public Clazz(TranslatorFactory factory) {
		super(factory);
	}

	@Override
	public String translate() {
		final StringBuilder result = new StringBuilder(format("class $2 {"));
		final String constructor = format("public $2(");
		skipRequired();

		final List<Field> fields = new ArrayList<Field>();

		TokenType type = require();
		while (type != RPAREN) {
			final Field field = new Field();

			if (type == PUBLIC || type == PRIVATE || type == PROTECTED) {
				field.visibility = get();
				type = require();
			} else
				field.visibility = "public";

			field.type = get();
			require();
			field.name = get();

			fields.add(field);

			type = require();
			if (type == COMMA)
				type = require();
		}

		for (Field field : fields) {
			result.append(field.visibility).append(' ').append("final ").append(field.type).append(' ')
					.append(field.name).append(';');
		}

		result.append(constructor);

		boolean first = true;
		for (Field field : fields) {
			if (first)
				first = false;
			else
				result.append(',');
			result.append(field.type).append(' ').append(field.name);
		}

		result.append(") {");

		for (Field field : fields)
			result.append("this.").append(field.name).append('=').append(field.name).append(';');

		result.append("}}");

		return result.toString();
	}

	@Override
	public boolean test() {
		return test(CLASS, IDENTIFIER, LPAREN);
	}
}
