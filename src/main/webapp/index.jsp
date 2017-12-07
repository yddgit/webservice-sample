<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<style type="text/css">
fieldset {width:200px;display:inline-block;}
input {width:180px;}
textarea {width:600px;height:200px;}
button {display:block;margin:2px;}
div {margin-top:5px;padding:5px;border:1px #ccc solid;width:610px;background-color:#ccc;}
.request-message {color:#005AB5;}
.response-message {color:#01B468;}
.soap-fault {color:#D94600;}
</style>
<script type="text/javascript" src="static/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="static/js/jquery.soap.js"></script>
<script type="text/javascript" src="static/js/sample.js"></script>
<title>Web Service Sample</title>
</head>
<body>
	<h1>Web Service Sample</h1>
	<h2>WSDL URL</h2>
	<div>
		<ul>
			<li><a target="_blank" href="userService">userService</a></li>
			<li><a target="_blank" href="userService?xsd=1">userService Types</a></li>
		</ul>
	</div>
	<h2>Methods</h2>
	<div>
		<button onclick="sendList()">send list</button>
	</div>
	<div>
		<fieldset>
			<legend>Header:</legend>
			<label>username:<input id="add-header-username" name="add-header-username" type="text" value="admin"/></label>
			<label>password:<input id="add-header-password" name="add-header-password" type="text" value="123456"/></label>
			<label>nickname:<input id="add-header-nickname" name="add-header-nickname" type="text" value="超级管理员"/></label>
		</fieldset>
		<fieldset>
			<legend>Body:</legend>
			<label>username:<input id="add-username" name="add-username" type="text" value="Bob"/></label>
			<label>password:<input id="add-password" name="add-password" type="text" value="111222"/></label>
			<label>nickname:<input id="add-nickname" name="add-nickname" type="text" value="鲍勃"/></label>
		</fieldset>
		<button onclick="sendAdd()">send add</button>
	</div>
	<div>
		<fieldset>
			<legend>Body:</legend>
			<label>username:<input id="login-username" name="login-username" type="text" value="Bob"/></label>
			<label>password:<input id="login-password" name="login-password" type="text" value="111222"/></label>
		</fieldset>
		<button onclick="sendLogin()">send login</button>
	</div>
	<div>
		<fieldset>
			<legend>Header:</legend>
			<label>username:<input id="delete-header-username" name="delete-header-username" type="text" value="admin"/></label>
			<label>password:<input id="delete-header-password" name="delete-header-password" type="text" value="123456"/></label>
			<label>nickname:<input id="delete-header-nickname" name="delete-header-nickname" type="text" value="超级管理员"/></label>
		</fieldset>
		<fieldset>
			<legend>Body:</legend>
			<label>username:<input id="delete-username" name="delete-username" type="text" value="Bob"/></label>
		</fieldset>
		<button onclick="sendDelete()">send delete</button>
	</div>
	<h3>Request SOAPMessage</h3>
	<div>
		<textarea id="request-message" class="request-message"># Request SOAPMessage</textarea>
	</div>
	<h3>Response SOAPMessage</h3>
	<div>
		<textarea id="response-message" class="response-message"># Response SOAPMessage</textarea>
	</div>
</body>
</html>