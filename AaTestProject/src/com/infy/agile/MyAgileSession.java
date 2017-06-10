package com.infy.agile;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.agile.api.APIException;
import com.agile.api.AgileSessionFactory;
import com.agile.api.IAgileSession;

public class MyAgileSession {
	public static IAgileSession session;
	
	public static IAgileSession getSession(String env) throws APIException {
		String url = null;
		String user = null;
		String pass = null;
		if("DEV".equals(env)){
			url = "https://agiledev.nbty.net/Agile";
			user = "administrator";
			System.out.println("Enter Password for "+user);
			Scanner stdin = new Scanner(System.in);
			pass = stdin.nextLine();
		}
		return getSession(url, user, pass);
	}
	
	public static IAgileSession getSession(String url, String user, String pass) throws APIException {
		if(session==null){
			Map params = new HashMap();
			params.put(AgileSessionFactory.URL, url);
			params.put(AgileSessionFactory.USERNAME, user);
			params.put(AgileSessionFactory.PASSWORD, pass);
			session = AgileSessionFactory.createSessionEx(params);
		}
		return session;
	}
}

