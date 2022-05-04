package com.searchengine.WebSearchEngine;
/**
 * Crawler crawls the given link for more unique links.
 * When all the unique links are found in the the given link, all the links 
 * are converted to .txt files for further processing.
 * 
 * */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
	static HashSet<String> uniqueLinks = new HashSet<String>(); 
		
	public static void Crawling(String urlToCrawl, int maxLimit)
	{
		uniqueLinks.add(urlToCrawl);
		try {			
			 Document doc = Jsoup.connect(urlToCrawl).get();
			 String pattern = ".*" + urlToCrawl.replaceAll("^(http|https)://", "") + ".*";
			 System.out.println("\nURL Pattern to parse: "+ pattern);

			 Elements linksOnPage = doc.select("a[href]");
			 String currentURL;
			 for (Element page : linksOnPage) {
				 currentURL = page.attr("abs:href");
				 if(uniqueLinks.contains(currentURL)) {
					 System.out.println("\nURL: " + currentURL + " ----> already visited");
				 } 
				 else if(!Pattern.matches(pattern, currentURL)) {
					 System.out.println("\nURL: " + currentURL + " ----> is irrevant. Will not be parsed.");
				 }
				 else {
					 uniqueLinks.add(page.attr("abs:href"));
					 System.out.println("\nURL: " + currentURL + " ---->  will be crawled");
				 }
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void htmlToTextConversion()
	{
		try {
			String textString, urlString;
			String FolderPath = System.getProperty("user.dir") + "\\Converted_Text_Files\\";
			Iterator<String> itr1 = uniqueLinks.iterator();
			while(itr1.hasNext())
			{
				urlString = itr1.next();
				Document document = Jsoup.connect(urlString).get();
				textString = document.text();
				String file = document.title().replaceAll("[^a-zA-Z0-9_-]", "")+".txt";
				BufferedWriter out = new BufferedWriter( 
		                new FileWriter(FolderPath + file, true)); 
		        out.write(urlString + " " + textString); 
		        out.close(); 
			}
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("HTML Pages have successfully been converted into Text files.");
			System.out.println("Text Files are stored at the following path: "+FolderPath);
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	public static void crawl(String urlToCrawl)
	{
		int maxLimit = 1000;
		Crawling(urlToCrawl, maxLimit);
		htmlToTextConversion();
	}

}
