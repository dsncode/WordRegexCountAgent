package com.dsncode.regex.agent;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author "Daniel Silva Navarro"
 * @email daniel@dsncode.com
 * @url http://www.dsncode.com
 * 
 */
public class WordCountAgent {

	
	private List<String> dictionary;
	private String rule;
	
	public WordCountAgent()
	{
		
	}
	
	private static String getRule(List<String> dictionary)
	{
		StringBuilder rule = new StringBuilder();
		rule.append("(?i)");
		boolean first = true;
		for(String word : dictionary)
		{
			String word_rule = createWordRule(word);
			if(first==false)
			{
				rule.append("|");
				rule.append(word_rule);
			}
			if(first)
			{
				rule.append(word_rule);
				first = false;
			}
		}
		return rule.toString();
	}
	
	public static String createWordRule(String word)
	{
		String rule = null;
		
		if(word==null || word.isEmpty())
			return "";
		
		char[]word_char = word.toCharArray();
		boolean isFullWordMatchRequired=true;
		int x = 0;
		for(x=0;x<word_char.length;x++)
		{
			if((Character.UnicodeBlock.of(word_char[x])) == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS)
			{
				isFullWordMatchRequired = false;
			}
		}
		
		if(word.contains("$"))
		{
			word = word.replaceAll("\\$", "\\\\\\$");
			rule = "("+word+")";
			return rule;
		}
		
		
		if(isFullWordMatchRequired)
		{
			rule = "(\\b"+word+"\\b)";
		}
		else
		{
			rule = "("+word+")";
		}
		
		return rule;
	}
	
	public void setDictionary(List<String> dictionary)
	{
		this.dictionary = dictionary;
		
		if(this.dictionary!=null)
			this.rule=getRule(this.dictionary);
	}
	
	public Map<String,Integer> scoreText(String text)
	{
		Map<String,Integer> score = null;
		
		score = scoreText(this.rule,text);
		
		return score;
		
	}
	
	public static Map<String,Integer> scoreText(List<String> dictionary, String text)
	{
		Map<String,Integer> scores = null;
		String rule = getRule(dictionary);
		
		scores = scoreText(rule,text);
		
		return scores;
	}
	
	private static Map<String,Integer> scoreText(String rule, String text)
	{
		Map<String,Integer> scoreList = new HashMap<String,Integer>();
		if(rule==null || text==null)
			return scoreList;
		
		Pattern p = Pattern.compile(rule);
		Matcher m = p.matcher(text); // get a matcher object
        while(m.find()) {
       		String group =m.group();

       			if(scoreList.containsKey(group))
       				scoreList.put(group,scoreList.get(group)+1);
        		else
        			scoreList.put(group, 1);
        	}
        
		return scoreList;
	}

	
}
