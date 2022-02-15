package com.sai.sharePad.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sai.sharePad.dto.ShortenServiceRequestDTO;
import com.sai.sharePad.dto.ShortenServiceResponseDTO;
import com.sai.sharePad.service.SharePadService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Component
public class SharePadServiceImpl implements SharePadService
{
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	@CircuitBreaker(name = "shorteningService", fallbackMethod = "handleDownTime")
	public String share(String text)
	{
		ShortenServiceRequestDTO requestDTO = new ShortenServiceRequestDTO(text);
		
		String hash =  restTemplate.postForObject("https://ll-api-gateway.herokuapp.com/url/text",requestDTO,ShortenServiceResponseDTO.class).getShortURL();
		
		return "https://ll-share-pad.herokuapp.com/shared/"+hash;
	}
	
	public String handleDownTime(String LongURL,Exception e)
	{
		System.out.println("Handling downtime..");
		return "Services.. starting up please try again..";
	}

	@Override
	@CircuitBreaker(name = "redirectServiceDown", fallbackMethod = "handleDownTime")
	public String getTextFromHash(String hash)
	{
		return restTemplate.getForObject("https://ll-api-gateway.herokuapp.com/text/"+hash, ShortenServiceResponseDTO.class).getLongURL();
	}
}