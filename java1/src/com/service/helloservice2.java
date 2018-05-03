package com.service;
import javax.jws.WebService;
@WebService(name="helloservice")
public class helloservice2 {
	public String sayhello(String name) {
		return "hello" +name;
	}
	public int sum(int a, int b) {
		return a +b;
	}
}
