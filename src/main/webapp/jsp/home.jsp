<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Share Pad</title>
		<link href="../css/home.css" rel="stylesheet" type="text/css"></link>
		<script src="../js/home.js" type="text/javascript"></script>
	</head>
	<body>
		<div class="header">
			<h1 align="center">Share Pad</h1>
			<h3 align="center">Text Sharing Application</h3>
		</div>
		<div class="form">
			<form action="" method="post" id="mainForm">
				<%String text = (String)request.getAttribute("text"); %>
				<input type="hidden" name="text" value ="<%=text %>">
				<table>
					<tr>
						<td>
						<%if(text != null && !text.isEmpty()){%>
							<div class="text" id="text" contenteditable="true" spellcheck="false" data-placeholder="Paste or type text to be shared.."><span><%=text %></span></div>
						<%}else{ %>
							<div class="text" id="text" contenteditable="true" spellcheck="false" data-placeholder="Paste or type text to be shared.."></div>
						<%} %>
						</td>
						<td>
							<div class="actions" align="center">
								<input type="text" id="URL" value="${URL}" readonly placeholder="Share this Link once formed.."/>
								<br>
								<input type="button" id="submitButton" value="Share Text"/>
								<input type="button" id="copyLink" value="Copy Link"/>								
								<br>
								<input type="button" id="copyText" value="Copy Text"/>
								<input type="button" id="clearText" value="Clear Text"/>
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div align="center">
			<p>Saikrishna Akkenapelly | saikrishna.akkenapelly@gmail.com</p>
		</div>
	</body>
</html>