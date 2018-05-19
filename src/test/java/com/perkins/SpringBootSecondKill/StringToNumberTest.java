package com.perkins.SpringBootSecondKill;

public class StringToNumberTest {

	public static void main(String[] args) {
		String id = "1:3,2:4,3:5,4:7,5:8";
		String[] ids = id.split(",");
		for (String idstr : ids) {
			String[] g = idstr.split(":");
			System.out.println(g[0]+"===="+g[1]);
		}
		
	}
}
