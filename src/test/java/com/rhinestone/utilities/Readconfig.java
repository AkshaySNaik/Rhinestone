package com.rhinestone.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Readconfig {

	Properties prop;
	String path = System.getProperty("user.dir") + "//config//read.properties";

	public Readconfig() {
		prop = new Properties();
		try {
			File file = new File(path);
			FileInputStream fis = new FileInputStream(file);
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getURL() {
		String urlname = prop.getProperty("url");
		if (urlname == null)
			throw new RuntimeException("URL Not Found In The Properties File");
		else
			return urlname;
	}

	public String getBrowser() {
		String brwsename = prop.getProperty("browser");
		if (brwsename == null)
			throw new RuntimeException("Browser Not Found In The Properties File");
		else
			return brwsename;
	}

	public String getPageTitle() {
		String pagetitle = prop.getProperty("pgtitle");
		if (pagetitle == null)
			throw new RuntimeException("PageTitle Not Found In The Properties File");
		else
			return pagetitle;
	}

}
