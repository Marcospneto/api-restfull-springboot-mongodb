package com.jvm.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URL {
	/*
	 * Esse metodo ou ele vai retornar uma String decodificada ou vai retornar vazio
	 * caso tenha algum problema. O meu back-end tem que ser capaz de pegar o valor
	 * codificado enviado por parametro encodado e decodificar, resumindo, esse metodo vai
	 * decodificar o parametro da url.
	 */
	public static String decodeParam(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return "";
		}
	}

}
