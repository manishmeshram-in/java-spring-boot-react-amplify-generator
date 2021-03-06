package com.code.generator;

import java.io.File;
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
	final static String CD = "cd ";
	final static String PWDIR = System.getProperty("user.dir");
	//final static String CMDSEPARATOR = " & ";
	final static String PSSEPARATE = " ; ";
	//final static String POWERSHELL1 = "powershell.exe ";
	final static String POWERSHELL = "powershell.exe ";
	//final static String LS = "ls";
	//final static String DIR = "dir";
	final static String PWD = "pwd";
	//final static String AMPLIFY_INIT = "amplify init";
	final static String NPX_CREATE_REACT_APP = "npx create-react-app ";
	final static String AMPLIFY_INIT = "amplify init --yes --projectName myapp1 --envName dev --defaultEditor Visual Studio Code --appType javascript --framework react --sourceDir src --distributionDir build --buildCommand --buildCommand npm run-script build --startCommand npm run-script start --useProfile true --profileName default";
	final static String AMPLIFY_ADD_API = "cat APPLICATION_NAME.json | jq -c | amplify add api --headless";
	
	//amplify push --yes --generateCode true --codeLanguage javascript --fileNamePattern "src/graphql/**/*.js" --generatedFileName "API" --generateDocs true
	final static String AMPLIFY_PUSH = "amplify push --yes --generateCode true --codeLanguage javascript --fileNamePattern \"src/graphql/**/*.js\" --generatedFileName \"API\" --generateDocs true";
	final static String AMPLIFY_CONSOLE_API = "echo GraphQL | amplify console api";
	
	final static String STAGING = "staging";
	
	final static String FRONT_SLASH = "/";
	final static String BACK_SLASH = "\\";
	
	public static void executeCreateReactApp(Programming programming){
		
		String applicationName = programming.getApplicationName();
		String createReactAppCommand = NPX_CREATE_REACT_APP + applicationName;

		navigateExecuteShell(PWDIR + FRONT_SLASH + STAGING, PWD);
		navigateExecuteShell(PWDIR + FRONT_SLASH + STAGING, createReactAppCommand);
		navigateExecuteShell(PWDIR + FRONT_SLASH + STAGING, CD + applicationName);
		navigateExecuteShell(PWDIR + FRONT_SLASH + STAGING + FRONT_SLASH + applicationName, PWD);
		navigateExecuteShell(PWDIR + FRONT_SLASH + STAGING + FRONT_SLASH + applicationName, AMPLIFY_INIT);
		
		//CopyReplace.copyReplace("myapp", "myapp3");
		CopyReplace.copyReplace("myapp", applicationName);
		
		//String amplifyAddApi = "cat " + applicationName + ".json | jq -c | amplify add api --headless"; 
		//String amplifyAddApi = AMPLIFY_ADD_API.replaceAll("APPLICATION_NAME", applicationName);
		//cat myapp2.json | jq -c | amplify add api --headless
		navigateExecuteShell(PWDIR + FRONT_SLASH + STAGING + FRONT_SLASH + applicationName, AMPLIFY_ADD_API.replaceAll("APPLICATION_NAME", applicationName));
		
		//amplify push --yes --generateCode true --codeLanguage javascript --fileNamePattern "src/graphql/**/*.js" --generatedFileName "API" --generateDocs true
		//String AMPLIFY_PUSH = "amplify push --yes --generateCode true --codeLanguage javascript --fileNamePattern \"src/graphql/**/*.js\" --generatedFileName \"API\" --generateDocs true";
		
		navigateExecuteShell(PWDIR + FRONT_SLASH + STAGING + FRONT_SLASH + applicationName, AMPLIFY_PUSH);
		
		navigateExecuteShell(PWDIR + FRONT_SLASH + STAGING + FRONT_SLASH + applicationName, AMPLIFY_CONSOLE_API);
	}
	
	
	private static void navigateExecuteShell(String directory, String psCommand) {

		System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println("56: navigating to: "+directory);
		System.out.println("56: executing: "+psCommand);
		System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		
		String executionCommand =  CD + directory + PSSEPARATE + psCommand;
		System.out.println("60: executionCommand: "+executionCommand);
		
		ProcessBuilder processBuilder = new ProcessBuilder(executionCommand);
		//processBuilder.directory(new File(System.getProperty("user.home")));
		//CommandLine cmdLine = CommandLine.parse(executionCommand);
		//DefaultExecutor executor = new DefaultExecutor();
		try {
			//int exitValue = executor.execute(cmdLine);
			//exitValue = executor.execute(cmdLine1);
			Process process = processBuilder.start();
			
			int exitValue = process.exitValue();
			
			System.out.println("::::----::::");
			System.out.println("exitValue: "+exitValue);
		} catch (ExecuteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
	private static void navigateExecute(String directory, String psCommand) {

		System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println("56: navigating to: "+directory);
		System.out.println("56: executing: "+psCommand);
		System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		
		String executionCommand = POWERSHELL + CD + directory + PSSEPARATE + psCommand;
		System.out.println("60: executionCommand: "+executionCommand);
		
		CommandLine cmdLine = CommandLine.parse(executionCommand);
		DefaultExecutor executor = new DefaultExecutor();
		try {
			int exitValue = executor.execute(cmdLine);
			//exitValue = executor.execute(cmdLine1);
			System.out.println("::::----::::");
			System.out.println("exitValue: "+exitValue);
		} catch (ExecuteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	private static void executeCommand(String psCommand) {
		CommandLine cmdLine = CommandLine.parse(psCommand);
		DefaultExecutor executor = new DefaultExecutor();
		try {
			int exitValue = executor.execute(cmdLine);
			//exitValue = executor.execute(cmdLine1);
			System.out.println("::::");
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
