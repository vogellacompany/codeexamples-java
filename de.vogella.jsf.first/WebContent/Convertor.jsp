<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Celsius to Fahrenheit Convertor</title>
</head>
<body>
<f:view>
	<h:form>
		<h:panelGrid columns="2">
			<h:outputLabel value="Celsius"></h:outputLabel>
			<h:inputText  value="#{temperatureConvertor.celsius}"></h:inputText>
		</h:panelGrid>
		<h:commandButton action="#{temperatureConvertor.celsiusToFahrenheit}" value="Calculate"></h:commandButton>
		<h:commandButton action="#{temperatureConvertor.reset}" value="Reset"></h:commandButton>
		<h:messages layout="table"></h:messages>
	</h:form>
	
	
	<h:panelGroup rendered="#{temperatureConvertor.initial!=true}">
	<h3> Result </h3>
	<h:outputLabel value="Fahrenheit "></h:outputLabel>
	<h:outputLabel value="#{temperatureConvertor.fahrenheit}"></h:outputLabel>
	</h:panelGroup>
</f:view>
</body>
</html>
