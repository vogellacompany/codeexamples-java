package com.vogella.freemarker.first;

import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class MainTest {

	public static void main(String[] args) {

		// Configuration
		Writer file = null;
		Configuration cfg = new Configuration();

		try {
			// Set Directory for templates
			cfg.setDirectoryForTemplateLoading(new File("templates"));
			// load template
			Template template = cfg.getTemplate("helloworld.ftl");

			// data-model
			Map<String, Object> input = new HashMap<String, Object>();
			input.put("message", "vogella example");
			input.put("container", "test");

			// create list
			List<ValueExampleObject> systems = new ArrayList<ValueExampleObject>();

			systems.add(new ValueExampleObject("Android"));
			systems.add(new ValueExampleObject("iOS States"));
			systems.add(new ValueExampleObject("Ubuntu"));
			systems.add(new ValueExampleObject("Windows7"));
			systems.add(new ValueExampleObject("OS/2"));

			input.put("systems", systems);

			ValueExampleObject exampleObject = new ValueExampleObject(
					"Java object");
			input.put("exampleObject", exampleObject);

			// File output
			file = new FileWriter(new File("output.html"));
			template.process(input, file);
			file.flush();

			// Also write output to console
			Writer out = new OutputStreamWriter(System.out);
			template.process(input, out);
			out.flush();

		} catch (Exception e) {
			System.out.println(e.getMessage());

		} finally {
			if (file != null) {
				try {
					file.close();
				} catch (Exception e2) {
				}
			}
		}

	}
}