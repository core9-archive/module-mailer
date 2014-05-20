package io.core9.module.mailer;

public class MailTestNashorn {
	public static void main(String... args) throws Throwable {
		MethodInvoker temp = new MethodInvoker(
				"src/test/java/io/core9/module/mailer/mustache.min.js", 
				"src/test/java/io/core9/module/mailer/TEXTinput.txt", 
				"src/test/java/io/core9/module/mailer/JSONinput.json", 
				"JSON", 
				"render"
		);

		System.out.println(temp.executeInvoke());
	}

	
	
}
