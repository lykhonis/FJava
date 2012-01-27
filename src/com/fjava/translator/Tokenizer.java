package com.fjava.translator;

interface Tokenizer {

	Token firstToken();
	
	Token nextToken();
	
	Token getToken(int index);
	
	void skip(int count);
}
