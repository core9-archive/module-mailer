package io.core9.module.mailer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MethodInvoker {
	ScriptEngineManager engineManager;
	ScriptEngine engine;
	Invocable invocable;

	Object library;
	String invokeMethod;

	String template;
	String data;

	Object dataType;
	Object dataObject;


	public MethodInvoker(String libraryPath, String templatePath, String dataPath, String dataType, String theInvokeMethod) throws IOException, ScriptException{
		engineManager = new ScriptEngineManager();
		engine =  engineManager.getEngineByName("nashorn");
		invocable = (Invocable) engine;
		library = setLibrary(libraryPath);
		data = setData(dataPath);
		template = setTemplate(templatePath);
		invokeMethod = theInvokeMethod;
	}

	public void setJs(String fileString) throws FileNotFoundException, ScriptException{
		engine.eval(new FileReader(fileString));
	}

	public String setTemplate(String fileString) throws IOException{
		return template = readFile(fileString);
	}

	public String setData(String fileString) throws IOException{
		return data = readFile(fileString);
	}

	public Object setDataType(String fileType) throws ScriptException, NoSuchMethodException{
		if(fileType == "JSON"){
			dataObject = engine.eval(fileType);
			Object dataAsJson = 
					invocable.invokeMethod(dataObject, "parse", data);
			return dataAsJson;
		}
		return dataObject;
	}

	public void setDataModel(String fileType) throws ScriptException{
		dataObject = engine.eval(fileType);
	}

	public Object setLibrary(String libraryFile) throws ScriptException, FileNotFoundException{
		 return engine.eval(new FileReader(libraryFile));
	}

	public String readFile(String fileString) throws IOException
	{
		try(BufferedReader br = new BufferedReader(new FileReader(fileString))){
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			return sb.toString();
		}
	}

	public String executeInvoke() throws NoSuchMethodException, ScriptException{
		String result = (String) invocable.invokeMethod( library, invokeMethod, template, data);
		return result;
	}

}
