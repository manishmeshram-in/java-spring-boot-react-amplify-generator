package com.code.generator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RunCommand4 {



	final String CMD = "cmd.exe";
	final String C = "/c";
	final String CD = "cd \"";
	String drive;
	String [] folderHierarchy;
	String [] commandList = {"dir"};

	public String[] getCommandList() {
		return commandList;
	}

	public void setCommandList(String[] commandList) {
		this.commandList = commandList;
	}

	public RunCommand4(String drive, String [] folderHierarchy){
		this.drive = drive.toUpperCase();
	}

	public RunCommand4(String drive, String [] folderHierarchy, String [] commandList){
		this.drive = drive.toUpperCase();
		this.folderHierarchy = folderHierarchy;
		this.commandList = commandList;
	}

	public Process runCommand(){
		
		String folderPath = ":\\\\";
		String commands = "";
		
		for (String folder :folderHierarchy) {
			folderPath += folder + "\\\\";
		}
		System.out.println("folderPath: "+folderPath);
		
		for (String command :commandList) {
			commands += " && " + command;
		}
		System.out.println("commands: "+commands);

		//ProcessBuilder builder = new ProcessBuilder(CMD, C, "cd \"" + drive + ":\\Test\" && mkdir new3 && dir");
		//ProcessBuilder builder = new ProcessBuilder(CMD, C, "cd \"" + drive + ":\\Test\\new3\" && mkdir new3 && dir");
		//ProcessBuilder builder = new ProcessBuilder(CMD, C, "cd \"" + drive + folderPath + "\"" + " && mkdir new3 && dir");
		//ProcessBuilder builder = new ProcessBuilder(CMD, C, "cd \"" + drive + folderPath + "\"" + commands);
		ProcessBuilder builder = new ProcessBuilder(CMD, C, CD + drive + folderPath + "\"" + commands);

		builder.redirectErrorStream(true);
		Process p = null;

		try {
			p = builder.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p;
	}

	public void printCommandOutput(Process p) {
		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		try {
			while (true) {
				line = r.readLine();
				if (line == null) {
					break;
				}
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void runCommandPrintingOutput() {
		Process process = runCommand();
		printCommandOutput(process);
	}

}

