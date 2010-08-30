
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<f:view>
	<f:loadBundle basename="de.vogella.jsf.starter.messages" var="msg" />
	<h:form>
		<h:panelGrid columns="3">
			<h:outputLabel value="#{msg.left}"></h:outputLabel>
			<h:inputText id="left" value="#{card.left}"></h:inputText>
			<h:message for="left"></h:message>

			<h:outputLabel value="#{msg.right}"></h:outputLabel>
			<h:inputText id="right" value="#{card.right}">
			</h:inputText>
			<h:message for="right"></h:message>

		</h:panelGrid>
		<h:commandButton action="#{card.show}" value="#{msg.show}"></h:commandButton>
	    <h:commandButton action="#{card.clear}" value="#{msg.reset}"
			immediate="true"></h:commandButton>
		<h:messages layout="table"></h:messages>
	</h:form>

	<h:panelGrid rendered="#{card.result!=0}" columns="3">
		<h:outputLabel value="#{msg.result}"></h:outputLabel>
		<h:inputText id="result" value="#{card.result}">
		</h:inputText>
		<h:message for="result"></h:message>
	</h:panelGrid>


</f:view>
</body>
</html>