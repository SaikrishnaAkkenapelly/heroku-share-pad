document.addEventListener('DOMContentLoaded', function()
{
	var submitButton  = document.getElementById("submitButton");
	var mainForm = document.getElementById("mainForm");
	var text = document.getElementsByName("text")[0];
	var textArea = document.getElementById('text');
	const textAreaPlaceholder = textArea.getAttribute('data-placeholder');
	
	if(submitButton)
	{
	  	submitButton.addEventListener('click', function () 
		{
			if(textArea.innerHTML != "" && textArea.innerHTML != textAreaPlaceholder)
			{
				text.value = textArea.innerHTML;
				mainForm.action = "/share";
				mainForm.submit();
			}
			else
			{
				alert("No text to be shared..");
			}
		});
	}
	
	var clearTextButton = document.getElementById("clearText");
	
	if(clearTextButton)
	{
		clearTextButton.addEventListener('click', function () 
		{
			if(textArea.innerHTML != "" && textArea.innerHTML != textAreaPlaceholder)
			{
				clearTextButton.style.backgroundColor = "#66678e";
				textArea.innerHTML= "";
				setTimeout(function(){clearTextButton.style.backgroundColor = "#0556f3";},250);
			}
			else
			{
				alert("No text to be cleared..");
			}
		});
	}
	
	var copyTextButton = document.getElementById("copyText");
	
	if(copyTextButton)
	{
		copyTextButton.addEventListener('click', function () 
		{
			if(textArea.innerHTML != "" && textArea.innerHTML != textAreaPlaceholder)
			{
				copyTextButton.style.backgroundColor = "#66678e";
				var range = document.createRange();
				range.selectNode(document.getElementById("text"));
				window.getSelection().removeAllRanges();
				window.getSelection().addRange(range);
				document.execCommand('copy');
				window.getSelection().removeAllRanges();
				alert("Copied Text: " + textArea.innerHTML);
				setTimeout(function(){copyTextButton.style.backgroundColor = "#0556f3";},250);
			}
			else
			{
				alert("No text to be copied..");
			}
		});
	}
	
	var copyLinkButton = document.getElementById("copyLink");
	var url = document.getElementById("URL");
	
	if(copyLinkButton)
	{
		copyLinkButton.addEventListener('click', function () 
		{
			if(url.value != "")
			{
				copyLinkButton.style.backgroundColor = "#66678e";
				url.select();
				url.select(0,99999);
				document.execCommand("copy");
				alert("Copied URL: " + url.value);
				setTimeout(function(){copyLinkButton.style.backgroundColor = "#0556f3";},250);			
			}
			else
			{
				alert("No Link to be copied..");
			}
		});
	}
	



	// Set the placeholder as initial content if it's empty
	textArea.innerHTML === '' && (textArea.innerHTML = textAreaPlaceholder);

	textArea.addEventListener('focus', function (e)
	{
	    const value = e.target.innerHTML;
	    value === textAreaPlaceholder && (e.target.innerHTML = '');
	});

	textArea.addEventListener('blur', function (e)
	{
	    const value = e.target.innerHTML;
	    value === '' && (e.target.innerHTML = textAreaPlaceholder);
	});
});