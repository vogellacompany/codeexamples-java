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
	<xsl:template match="pages">
		<xsl:element name="pages">
		 	<xsl:copy-of select="@*" />
		 	<xsl:text>
		 	</xsl:text> 
<categories name="Introduction">
			<xsl:apply-templates />
</categories>
			<xsl:text>
		 	</xsl:text> 
		</xsl:element>
	</xsl:template>

	
</xsl:stylesheet>
