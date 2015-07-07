package com.dsncode.regex.example;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.dsncode.regex.agent.WordCountAgent;
/**
 * @author "Daniel Silva Navarro"
 * @email daniel@dsncode.com
 * @url http://www.dsncode.com
 * 
 */
public class StaticExample {

	
	public static void main(String args[])
	{
		
		List<String> words = new LinkedList<String>();
		String text = "the nice dog was reading a doc. who is that dog anyway?";
		words.add("doc");
		words.add("dog");
		
		Map<String,Integer> scores = WordCountAgent.scoreText(words, text);
		System.out.println(scores);
	}

}
