package com.sai.sharePad.service;

import org.springframework.stereotype.Service;

@Service
public interface SharePadService
{
	public String share(String text);
	public String getTextFromHash(String hash);
}