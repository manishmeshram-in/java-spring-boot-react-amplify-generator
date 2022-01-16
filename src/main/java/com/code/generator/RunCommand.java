package com.code.generator;

import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;

import com.code.generator.model.Programming;

public class RunCommand {

	public static void main(String[] args) {
		//new RunCommand().run();
	}
	
	final static String SPACE = " ";
	final static String CD = "cd";
	final static String PWD = System.getProperty("user.dir");
	final static String SEPARATOR = ";";
	final static String POWERSHELL = "powershell.exe";
	final static String LS = "ls";
	final static String FRONT_SLASH = "/";
	final static String BACK_SLASH = "\\";
	
	public static void executeSpringInit(Programming programming){
	
		//Creating the spring application
		System.out.println("Present working directory : pwd : "+PWD);
		String springInitCommand = "spring init --dependencies=";
		String dependency = "";
		String applicationName = programming.getApplicationName();
		
		for (int i=0; i < programming.getDependencies().size(); i++) {
			dependency = programming.getDependencies().get(i);
			springInitCommand += dependency;
			if (i < programming.getDependencies().size()-1) {
				springInitCommand += ",";
			}
		}
		springInitCommand += SPACE + applicationName;
		System.out.println("springInitCommand: "+springInitCommand);
		String psCommand = POWERSHELL +  SPACE + CD + SPACE + PWD + SEPARATOR + SPACE + LS + SEPARATOR + SPACE + springInitCommand + SEPARATOR + SPACE + LS;
		System.out.println("psCommand: "+psCommand);
		
		executeCommand(psCommand);
		
		//Creating the API framework folder structure within the spring application
		createFrameworkDirectories(applicationName, "model");
		createFrameworkDirectories(applicationName, "rest");
		createFrameworkDirectories(applicationName, "service");
		createFrameworkDirectories(applicationName, "springdatajpa");
		
	}
	
	
	private static void createFrameworkDirectories(String applicationName, String specificFrameworkFolder) {
		
		String mainFolderName = applicationName.replace("-", "").toLowerCase();		
		String createDirectoryCommand = "New-Item  -Name \"DIRECTORY_NAME\" -ItemType \"directory\"";
		createDirectoryCommand = createDirectoryCommand.replace("DIRECTORY_NAME", specificFrameworkFolder);
		String psCommand = POWERSHELL +  SPACE + CD + SPACE + PWD + "\\" + applicationName + "\\src\\main\\java\\com\\example" + "\\" + mainFolderName + SEPARATOR + SPACE + createDirectoryCommand;
		executeCommand(psCommand);
	}
	
	private static void executeCommand(String psCommand) {
		CommandLine cmdLine = CommandLine.parse(psCommand);
		DefaultExecutor executor = new DefaultExecutor();
		try {
			int exitValue = executor.execute(cmdLine);
			//exitValue = executor.execute(cmdLine1);
			System.out.println("exitValue: "+exitValue);
		} catch (ExecuteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
}
