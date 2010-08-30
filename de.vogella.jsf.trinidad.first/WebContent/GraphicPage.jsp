<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/trinidad" prefix="tr"%>
<%@ taglib uri="http://myfaces.apache.org/trinidad/html" prefix="trh"%>
<html>
<f:view>
	<body>
	<tr:document>
		<tr:form>
			<h:panelGrid columns="3">

				<tr:chart legendPosition="start" value="#{myChartModel}" type="pie" />
				<tr:chart value="#{myChartModel}" type="bar" />
				<tr:chart value="#{myChartModel}" type="radar" />
				<tr:chart value="#{myChartModel}" type="radarArea" />
				<tr:chart value="#{myChartModel}" type="funnel" />
				<tr:chart value="#{myChartModel}" type="circularGauge" />
				<tr:chart value="#{myChartModel}" type="semiCircularGauge"
					YMinorGridLineCount="1" />
			</h:panelGrid>
		</tr:form>
	</tr:document>
	</body>
</f:view>
</html>
