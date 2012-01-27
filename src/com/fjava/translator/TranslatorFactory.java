package com.fjava.translator;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class TranslatorFactory {

	private static final List<Class<? extends Translatable>> PLUGINS = new ArrayList<Class<? extends Translatable>>();
	static {
		PLUGINS.add(Closure.class);
		PLUGINS.add(Closure1.class);
		PLUGINS.add(Closure2.class);
		PLUGINS.add(ClosureAnonymous.class);
		PLUGINS.add(ClosureAnonymous1.class);
		PLUGINS.add(ClosureAnonymous2.class);
		PLUGINS.add(ClosureType.class);
		PLUGINS.add(ClosureTypeWithoutParentheses.class);
		PLUGINS.add(ClosureType1.class);
		PLUGINS.add(ClosureType2.class);
		PLUGINS.add(Lazy.class);
	}

	private final List<Translatable> translateables;
	final Tokenizer tokenizer;

	public TranslatorFactory(Tokenizer tokenizer) {
		this.tokenizer = tokenizer;
		final List<Translatable> plugins = new ArrayList<Translatable>();

		for (Class<? extends Translatable> plugin : PLUGINS) {
			try {
				plugins.add(plugin.getConstructor(TranslatorFactory.class).newInstance(this));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}

		translateables = Collections.unmodifiableList(plugins);
	}

	public String translate() {
		final Token token = tokenizer.getToken(0);
		if (token.type == TokenType.SINGLE_LINE_COMMENT || token.type == TokenType.MULTI_LINE_COMMENT)
			return "";

		for (Translatable translateable : translateables)
			if (translateable.test())
				return translateable.translate();

		return token.image + token.type.postfix;
	}
}
