package com.searchengine.WebSearchEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchWord {

	/**
	 * Searches word in the given file using Booyer Moore algorithm. 
	 * Returns the number of times word has been found in the file.
	 * @param filePath
	 * @param word
	 * @return
	 */
	public static int wordSearch(File filePath, String word)
	{
		int counter=0;
		String data="";
		try
		{
			BufferedReader Object = new BufferedReader(new FileReader(filePath));
			String line = null;
			
			while ((line = Object.readLine()) != null){
				data= data+line;
			}
			Object.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception:"+e);
		}
		// Finding the position of the word...............
		String txt = data;
			
		int offset1a = 0;
		
		for (int loc = 0; loc <= txt.length(); loc += offset1a + word.length()) 
		{
			offset1a = WebSearchEngine.search1(word, txt.substring(loc)); 
			if ((offset1a + loc) < txt.length()) {
				counter++;
				System.out.println("\n"+word+ " at position " + (offset1a + loc));  //printing position of word
			}
		}
		if(counter!=0)	{		
			System.out.println("-------------------------------------------------");
			System.out.println("\nFound in "+filePath.getName()); // Founded from which text file..		
			System.out.println("-------------------------------------------------");
		}
		return counter;
	}
}
