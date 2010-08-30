
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<f:view>
	<f:loadBundle basename="de.vogella.jsf.starter.messages" var="msg" />
	<h:form>
		<h:panelGrid columns="3">
			<h:outputLabel value="#{msg.user}"></h:outputLabel>
			<h:inputText id="user" value="#{user.name}" required="true" requiredMessage="required">
			<f:validator
					validatorId="de.vogella.jsf.starter.validator.LoginValidator" />
			</h:inputText>
			<h:message for="user"></h:message>
			<h:outputLabel value="#{msg.password}"></h:outputLabel>
			<h:inputSecret id="password" value="#{user.password}" required="true" requiredMessage="required">
			</h:inputSecret>
			<h:message for="password"></h:message>
		</h:panelGrid>
		<h:commandButton action="#{user.login}" value="#{msg.login}"></h:commandButton>
	</h:form>
</f:view>
</body>
</html>