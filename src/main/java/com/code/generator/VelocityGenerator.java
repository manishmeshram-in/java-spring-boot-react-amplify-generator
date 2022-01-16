package com.code.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.text.CaseUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import com.code.generator.model.DomainModel;
import com.code.generator.model.DomainObject;
import com.code.generator.model.Field;
import com.code.generator.model.Programming;

public class VelocityGenerator {

	static String inputTemplate = "./devfac_blueprint/templates/com/example/myfolder/model/ENTITY.java.vm";
	static String className = "Employee";
	static String outputFile = "./my-application/src/main/java/com/example/myapplication/model/" + className + ".java";

	public static void main(String[] args) {
		// generateCode();
	}

	public static void generateCode(DomainModel domainModel) {

		VelocityEngine velocityEngine = new VelocityEngine();
		velocityEngine.init();

		DomainObject domainObject = domainModel.get(0);
		List<Field> fields = domainObject.getFields();
		// List<String> fieldNames = new ArrayList<String>();
		String[] fieldNames = new String[fields.size()];
		String[] fieldTypes = new String[fields.size()];
		String[] fieldDeclarations = new String[fields.size()];

		for (int i = 0; i < fields.size(); i++) {
			// fieldNames[i] = (makeCamelCase(fields.get(i).getName()));
			// fieldTypes[i] = database2javaType(fields.get(i).getType().get(0));
			fieldDeclarations[i] = database2javaType(fields.get(i).getType().get(0)) + " "
					+ (makeCamelCase(fields.get(i).getName()));
		}

		VelocityContext context = new VelocityContext();

		// context.put("fieldNames", fieldNames);
		context.put("fieldDeclarations", fieldDeclarations);

		Writer writer;
		try {
			writer = new FileWriter(new File(outputFile));
			Velocity.mergeTemplate(inputTemplate, "UTF-8", context, writer);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Generated: " + outputFile);
	}

	private static String makeCamelCase(String string) {

		return CaseUtils.toCamelCase(string, false, new char[] { '_' });
	}

	private static String database2javaType(String type) {

		type = type.toUpperCase();

		return null;
		/*
		 * switch (type) { case "INTEGER": return "Integer"; case "INT": return
		 * "Integer"; case "VARCHAR": return "String"; case "VARCHAR2": return "String";
		 * case "CHARACTER VARYING": return "String"; default: return "String";
		 */
	}
}
