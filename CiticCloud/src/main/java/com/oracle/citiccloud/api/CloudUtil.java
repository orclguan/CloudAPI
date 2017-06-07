package com.oracle.citiccloud.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CloudUtil {
	public static void auth() {
		Properties prop = new Properties();
		InputStream input = null;
		input = CloudUtil.class.getClassLoader().getResourceAsStream("/oracle_cloud.properties");
	
		try {
			prop.load(input);

			String domain = prop.getProperty("IDENTITY_DOMAIN");
			System.out.println("domain:" + domain);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
