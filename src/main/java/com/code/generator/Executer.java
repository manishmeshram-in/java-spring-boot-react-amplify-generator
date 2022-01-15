package com.code.generator;

public class Executer {

	public static void main(String args[]) {

		String drive = "c";
		String [] folderHierarchy = {"Test", "new3"};
		String [] commandList = {"mkdir new6", "dir"};
		RunCommand3 rtc3 = new RunCommand3(drive, folderHierarchy, commandList);
		
		rtc3.runCommandPrintingOutput();
	}
}
