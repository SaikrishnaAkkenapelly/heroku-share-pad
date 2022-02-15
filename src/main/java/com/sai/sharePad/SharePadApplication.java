package com.sai.sharePad;

import java.util.concurrent.CompletableFuture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.sai.sharePad.dto.ShortenServiceResponseDTO;

@SpringBootApplication
public class SharePadApplication
{
	public static void main(String[] args)
	{
		CompletableFuture.runAsync(SharePadApplication::wakeUpShorteningService);
		CompletableFuture.runAsync(SharePadApplication::wakeUpRedirectService);
		SpringApplication.run(SharePadApplication.class, args);
	}
	
	//code to wake shortening service on heroku
	private static void wakeUpShorteningService()
	{
		try
		{
			new RestTemplate().getForObject("https://ll-api-gateway.herokuapp.com/url/ping",ShortenServiceResponseDTO.class);
			System.out.println("Ping successfull for shortening service." + Thread.currentThread().getName());
		}
		catch(Throwable throwable)
		{
			System.out.println("ERROR while pinging shortening service.."+throwable.getMessage());
		}
	}
	
	//code to wake shortening service on heroku
	private static void wakeUpRedirectService()
	{
		try
		{
			new RestTemplate().getForObject("https://ll-api-gateway.herokuapp.com/ping",ShortenServiceResponseDTO.class);
			System.out.println("Ping successfull for redirect service." + Thread.currentThread().getName());
		}
		catch(Throwable throwable)
		{
			System.out.println("ERROR while pinging redirect service.."+throwable.getMessage());
		}
	}
		
	@Bean
	public RestTemplate getRestTemplate()
	{
	    return new RestTemplate();
	}
}
