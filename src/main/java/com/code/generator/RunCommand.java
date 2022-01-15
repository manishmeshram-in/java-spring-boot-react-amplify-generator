package com.code.generator;

import java.io.File;

public class RunCommand {

	public static void generateDocumentation(File oasFile, String outputDirectory, String outputFolderName, String documentationLanguage) {
		try {
			String command = "java -jar C:\\Users\\JANAN\\Desktop\\codegenjar\\swagger-codegen-cli-3.0.29.jar generate ";
			String fileNameCmd = "-i " + oasFile.getAbsolutePath();
			String outputLanguage = null;
			/*
			 * switch (documentationLanguage) { case "HTML": outputLanguage = " -l " +
			 * "html"; break; case "Dynamic-HTML": outputLanguage = " -l " + "dynamic-html";
			 * break; }
			 */
			String outputDirCmd = " -o " + outputFolderName;
			// outputLanguage = " -l "+"java";
			String documentationCommand = command + fileNameCmd + outputLanguage + outputDirCmd;

			File od = new File(outputDirectory);
			File documentationDirectory = new File(outputDirectory);
			Runtime runTime = Runtime.getRuntime();
			Process pr = runTime.exec(documentationCommand, null, documentationDirectory);

			if (documentationLanguage == "Dynamic-HTML") {

				ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "cd " + outputFolderName, " && npm install",
						"&& node .");
				pb.directory(documentationDirectory);

				// String openDynamicHTMLDoc = "cd "+outputFolderName;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
