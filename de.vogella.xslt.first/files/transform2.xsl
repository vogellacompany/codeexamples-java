<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">

	<xsl:output method="xml" />

	<!-- Copy everything -->
	<xsl:template match="*">
		<xsl:copy>
			<xsl:copy-of select="@*" />
			<xsl:apply-templates />
		</xsl:copy>
	</xsl:template>

	<!-- Do some adjustments for the address -->
	<xsl:template match="address">
		<xsl:element name="place-where-person-live">
			<xsl:apply-templates />
		</xsl:element>
	</xsl:template>

	<!-- Put the name in a <hubba> tag -->
	<xsl:template match="name">
		<xsl:element name="name">
			<hubba>
				<xsl:apply-templates />
			</hubba>
		</xsl:element>
	</xsl:template>
</xsl:stylesheet>
