package com.code.generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.code.generator.model.ApplicationBlueprint;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class Executer {

	final static String applicationBlueprintFilePath = "\\devfac_blueprint\\config\\application-blueprint.json";
	
	public static void main(String args[]) {
		execute();
	}
	
	public static void execute() {
		
		String pwd = System.getProperty("user.dir");
		System.out.println("Present working directory : pwd : "+pwd);
		String applicationBlueprintFileLocation = pwd + applicationBlueprintFilePath;
		
		File file = new File(applicationBlueprintFileLocation);
		
		Gson gson = new Gson();
		JsonReader reader = null;

		try {
			reader = new JsonReader(new FileReader(file));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		ApplicationBlueprint applicationBlueprint = gson.fromJson(reader, ApplicationBlueprint.class);
		System.out.println("applicationBlueprint: "+applicationBlueprint.toString());		

		createSpringApplication(applicationBlueprint);
			
		System.exit(0);
	}

	private static void createSpringApplication(ApplicationBlueprint applicationBlueprint) {
		RunCommand.executeCreateReactApp(applicationBlueprint.getConfiguration().getProgramming());
		//VelocityGenerator.generateCode(applicationBlueprint.getConfiguration().getDomainModel());
	}

}
