package com.trinet.connecto;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.TreeSet;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class ConnectoApplication {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	public static void main(String[] args) {
//		TreeSet<String> ts = new TreeSet<>();
//		ts.add("Geeks");
//		ts.add("For");
//		ts.add("Geeks");
//		ts.add("GeeksForGeeks");
//		for(String t:ts){
//			System.out.println(t+" ");
//		}

//		int arr[2];
//		System.out.println("arr[0] = " + arr[0]);
//		System.out.println("arr[0] = " + arr[1]);

		int a =5;
		int b = 10;
		first:
		{
			second:
			{
				if(a==b >> 1)
					break first;
			}
			System.out.println("a = " + a);
		}
		System.out.println("b = " + b);

//		SpringApplication.run(ConnectoApplication.class, args);
	}

}