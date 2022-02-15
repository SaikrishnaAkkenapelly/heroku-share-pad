package com.sai.sharePad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sai.sharePad.service.SharePadService;

@Controller
public class SharePadController
{
	@Autowired
	SharePadService sharePadService;
	
	@GetMapping(path = "/")
	public ModelAndView showHome()
	{
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("home");
		
		return modelAndView;
	}
	
	@PostMapping(path = "/share")
	public ModelAndView shareText(@RequestParam String text)
	{
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("text",text);
		modelAndView.addObject("URL",sharePadService.share(text));
		
		modelAndView.setViewName("home");
		
		return modelAndView;
	}
	
	@GetMapping(path = "/shared/{hash}")
	public ModelAndView sharedView(@PathVariable String hash)
	{
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("text",sharePadService.getTextFromHash(hash));
		modelAndView.addObject("URL","https://ll-share-pad.herokuapp.com/shared/"+hash);
		
		modelAndView.setViewName("home");
		
		return modelAndView;
	}
}