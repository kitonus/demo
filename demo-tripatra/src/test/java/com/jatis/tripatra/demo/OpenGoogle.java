package com.jatis.tripatra.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class OpenGoogle {

	public static void main(String[] args) throws IOException {
		URL googleUrl = new URL("https://www.google.com");
		InputStream input = googleUrl.openStream();
		
		//Read from google and print to standard output
		BufferedReader read = new BufferedReader(new InputStreamReader(input));
		try {
			String line = read.readLine();
			while (line != null) {
				System.out.println(line);
				line = read.readLine();
			}
			
		} finally {
			read.close();
		}
	}

}
