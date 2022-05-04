package com.searchengine.WebSearchEngine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.regex.Matcher;

public class WebSearchEngine {

	static ArrayList<String> key = new ArrayList<String>();
	static Hashtable<String, Integer> numbers = new Hashtable<String, Integer>();
	static int n = 1;
	static Scanner sc = new Scanner(System.in);
	static int R;
	static int[] right;

	public WebSearchEngine() {

		System.out.println("***************************************************");
		System.out.println("      WELCOME TO OUR WEB SEARCH ENGINE - ATOM      ");
		System.out.println("***************************************************");
		System.out.println("~~~~~~~~~~~~~~~~~~~TEAM MEMBERS~~~~~~~~~~~~~~~~~~~~");
		System.out.println("\n		   SNEHA ARORA					   ");
		System.out.println("		   MAYANK SEHGAL 				   ");
		System.out.println("		   PREETIKA CHHOTRAY		    		   ");
		System.out.println("\n***************************************************");
	}

	// Finding pattern using Boyer Moore method.
	public static int search1(String pat, String txt) {
		BoyerMoore b = new BoyerMoore(pat);
		int offset = b.search(txt);
		return offset;
	}

	public static void searchEngine() {

		WebSearchEngine w = new WebSearchEngine();
		Scanner scan = new Scanner(System.in);
		//String urlToCrawl = "http://geeksforgeeks.org/";
		System.out.println("Enter the URL to be crawled: ");
		String urlToCrawl = scan.nextLine();
		System.out.println("\n--------------------------CRAWLING HAS STARTED FOR THE URL: "+urlToCrawl+"------------------------");
		Crawler.crawl(urlToCrawl);
		System.out.println("\n--------------------------CRAWLING HAS STOPPED FOR THE URL: "+urlToCrawl+"------------------------");
		
		/** occurence variable stores the count of searched word in a file. <String filename, int frequency>	 */
		Hashtable<String, Integer> occurrence = new Hashtable<String, Integer>();
		String choice = "y";

		do {
			System.out.println("\n***************************************************");
			System.out.println("\nENTER THE WORD TO BE SEARCHED: ");
			String p = scan.nextLine();
			System.out.println("***************************************************");
			long fileNumber = 0;
			int wordfrequency = 0;
			int pg = 0;

			try {
				File dir = new File(System.getProperty("user.dir") + "\\Converted_Text_Files\\");

				File[] fileArray = dir.listFiles();
				for (int i = 0; i < fileArray.length; i++) {
					// Searching the word given as an input.
					wordfrequency = SearchWord.wordSearch(fileArray[i], p);
					occurrence.put(fileArray[i].getName(), wordfrequency);
					if (wordfrequency != 0)
						pg++;
					fileNumber++;
				}

				if (pg == 0) {
					System.out.println("\n\n\n---------------------------------------------------");
					System.out.println("Given word not found!!");
				} 
				else {
					//Ranking of Web Pages using merge sort 
					//Collections.sort by default uses merge sort
					Sorting.pageSort(occurrence,pg);
				}	
				System.out.println("\n\n Do you want to continue(y/n)??");
				choice = scan.nextLine();
			} catch(Exception e) {
				e.printStackTrace();
			}
		} while(choice.equals("y"));
		System.out.println("\n***************************************************\n");
		System.out.println("	:) THANK YOU FOR USING OUR SEARCH ENGINE - ATOM :)        ");
		System.out.println("\n***************************************************\n");
			
	}

	// MAIN METHOD.........
	public static void main(String[] args) {
			WebSearchEngine.searchEngine();		
	}
}
