package com.code.generator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RunCommand2 {

	public static void main(String args[]) {
		/*
		 * try {
		 * 
		 * String outputDirectory = "C:\\Test"; File documentationDirectory = new
		 * File(outputDirectory);
		 * 
		 * Runtime runTime = Runtime.getRuntime(); Process pr =
		 * runTime.exec("cmd.exe mkdir new1", null, documentationDirectory);
		 * 
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 */

		try {
			ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "cd \"C:\\Test\" && mkdir new2 && dir");
			builder.redirectErrorStream(true);
			Process p;

			p = builder.start();

			BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while (true) {
				line = r.readLine();
				if (line == null) {
					break;
				}
				System.out.println(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static Process runCommand() {
		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "cd \"C:\\Test\" && mkdir new2 && dir");
		builder.redirectErrorStream(true);
		Process p = null;

		try {
			p = builder.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
	

}
