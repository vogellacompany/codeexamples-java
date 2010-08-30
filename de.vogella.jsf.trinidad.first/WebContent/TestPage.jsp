<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/trinidad" prefix="tr"%>
<%@ taglib uri="http://myfaces.apache.org/trinidad/html" prefix="trh"%>
<html>
<f:view>
	<body>
	<tr:document>
		<tr:form>
			<tr:panelFormLayout>
				<tr:inputText labelAndAccessKey="&Firstame" required="true"></tr:inputText>
				<tr:inputText labelAndAccessKey="&Lastname"></tr:inputText>
			</tr:panelFormLayout>
			<tr:selectManyShuttle leadingHeader="Important Selection">
				<tr:selectItem label="Java" value="java"></tr:selectItem>
				<tr:selectItem label=".NET" value=".net"></tr:selectItem>
				<tr:selectItem label="Groovy" value="groovy"></tr:selectItem>
			</tr:selectManyShuttle>
		</tr:form>
		<tr:form>
			<tr:panelHeader text="Friends of Carlotta">
				<tr:table value="#{tableValues.persons}" var="person"
					rowBandingInterval="1">

					<tr:column sortProperty="firstName" sortable="true"
						headerText="Firstname">
						<h:outputText value="#{person.firstName}">
						</h:outputText>
					</tr:column>
					<tr:column sortProperty="lastName" sortable="true"
						headerText="Lastname">
						<h:outputText value="#{person.lastName}">
						</h:outputText>
					</tr:column>
				</tr:table>
		
		</tr:panelHeader>
		
	
		</tr:form>
	</tr:document>
	</body>
</f:view>
</html>