package com.fjava;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.fjava.translator.Translator;

public class Main {

	private static String readFileAsString(String filePath) throws IOException {
		final StringBuffer fileData = new StringBuffer();
		final BufferedReader reader = new BufferedReader(new FileReader(filePath));
		try {
			final char[] buf = new char[1024];
			int numRead = 0;
			while ((numRead = reader.read(buf)) != -1)
				fileData.append(String.valueOf(buf, 0, numRead));
		} finally {
			reader.close();
		}
		return fileData.toString();
	}

	private static void writeFileAsString(String filePath, String string) throws IOException {
		final FileWriter writer = new FileWriter(filePath);
		try {
			writer.write(string);
		} finally {
			writer.close();
		}
	}

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("F(unctional)Java translator v0.1. by Vladimir Lichonos.");
			System.out.println("\t- Simple tool should run before Java compiler to translate some structures\n"
					+ "\t  of the language to 'clean' Java code.");
			System.out
					.println("\t- All arguments passes to this tool has to be *.fjava files, that\n"
							+ "\t  can even have no FJava structures. Tool will create .java file with same name in same package (folder)\n"
							+ "\t  so newly created java file can be compiled by Java compiler.");
			System.out
					.println("\t- Be able to use this tool it's required to add com.fjava.library to classpath to your project.\n"
							+ "\t  There are no files to be attached to your code, except if you use, for example, () => {} structure,\n"
							+ "\t  tool will replace it with some interface F<...>(...) so this file will be compiled by Java compiler and attached\n"
							+ "\t  to your project. If you don't use any structured, additional files won't be attached at all.");
			return;
		}

		for (String arg : args) {
			try {
				Translator translator = new Translator(readFileAsString(arg));
				writeFileAsString(arg.replace(".fjava", ".java"), translator.translate());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
