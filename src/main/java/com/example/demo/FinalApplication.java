package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalApplication.class, args);
		Connection con = new Connection("서버 시작됨");
		System.out.println(con);
	}
}

class Connection{
	String Connect;

	Connection(String Connect){
		this.Connect = Connect;
	}

	@Override
	public String toString(){
		return Connect;
	}
}