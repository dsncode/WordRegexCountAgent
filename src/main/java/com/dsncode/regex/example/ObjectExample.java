package com.dsncode.regex.example;

import java.util.LinkedList;
import java.util.List;

import com.dsncode.regex.agent.WordCountAgent;
/**
 * @author "Daniel Silva Navarro"
 * @email daniel@dsncode.com
 * @url http://www.dsncode.com
 * 
 */
public class ObjectExample {

	public static void main(String[] args) {
		List<String> words = new LinkedList<String>();
		String text = "the nice dog was reading a doc. who is that dog anyway?";
		String text2 = "I don't know that dog... who is the owner?";
		words.add("doc");
		words.add("dog");
		words.add("owner");
		
		WordCountAgent agent = new WordCountAgent();
		agent.setDictionary(words);
		
		System.out.println("Score text1: "+agent.scoreText(text));
		System.out.println("Score text2: "+agent.scoreText(text2));

	}

}
