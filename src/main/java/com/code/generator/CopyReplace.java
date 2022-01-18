package com.code.generator;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyReplace {

	private static void copyFile(File srcFile, File destFile) throws IOException {

		InputStream oInStream = new FileInputStream(srcFile);
		OutputStream oOutStream = new FileOutputStream(destFile);

		// Transfer bytes from in to out
		byte[] oBytes = new byte[1024];
		int nLength;

		BufferedInputStream oBuffInputStream = new BufferedInputStream(oInStream);
		while ((nLength = oBuffInputStream.read(oBytes)) > 0) {
			oOutStream.write(oBytes, 0, nLength);
		}
		oInStream.close();
		oOutStream.close();
	}

	
	public static void main(String args[]) {
		//copyReplace("myapp", "myapp3");
	}
	
	
	public static void copyReplace(String myapp, String myappNew) {
		
		String stagingPath = "C:\\01Data\\16Dev\\GitHub\\java-spring-boot-react-amplify-generator\\staging\\";
		String sourcePath = stagingPath + myapp + ".json";
		String destinationPath = stagingPath + myappNew + "\\" + myappNew + ".json";
		
		
		File source = new File(sourcePath);
				//new File("C:\\01Data\\16Dev\\GitHub\\java-spring-boot-react-amplify-generator\\staging\\myapp.json");

		File destination = new File(destinationPath);
				//new File("C:\\01Data\\16Dev\\GitHub\\java-spring-boot-react-amplify-generator\\staging\\myapp3\\myapp3.json");

		if (!destination.exists()) {
			try {
				destination.createNewFile();
				copyFile(source, destination);
				//modifyFile(destination, "myapp", "myapp3");
				modifyFile(destination, myapp, myappNew);

			} catch (IOException e) {
				System.out.println("44: IOException :" + e);
				e.printStackTrace();
			}
		}
		
		//modifyFile(destination, "myapp", "myapp3");
		//modifyFile(destination, myapp, myappNew);

	}

	
	private static void modifyFile(String filePath, String oldString, String newString) {
	
		File fileToBeModified = new File(filePath);
		modifyFile(fileToBeModified, oldString, newString);
	}
	
	
	private static void modifyFile(File file, String oldString, String newString) {
		
		//File fileToBeModified = new File(filePath);
		File fileToBeModified = file;
		String oldContent = "";
		BufferedReader reader = null;
		FileWriter writer = null;

		try {
			reader = new BufferedReader(new FileReader(fileToBeModified));
			// Reading all the lines of input text file into oldContent
			String line = reader.readLine();

			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();
				line = reader.readLine();
			}

			// Replacing oldString with newString in the oldContent
			String newContent = oldContent.replaceAll(oldString, newString);

			// Rewriting the input text file with newContent
			writer = new FileWriter(fileToBeModified);

			writer.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// Closing the resources
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
