<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:import href="../docbook-xsl-1.76.1/epub/docbook.xsl"/>
	<xsl:template match="orderedlist/listitem/para
[count(preceding-sibling::*) = 0 and count(following-sibling::*) =
0]">
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="itemizedlist/listitem/para
[count(preceding-sibling::*) = 0 and count(following-sibling::*) =
0]">
		<xsl:apply-templates/>
	</xsl:template>
</xsl:stylesheet>