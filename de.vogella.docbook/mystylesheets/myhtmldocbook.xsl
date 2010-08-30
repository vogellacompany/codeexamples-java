<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">
	<xsl:import href="../docbook-xsl-1.74.0/html/docbook.xsl" />
	<xsl:template name="user.header.content">
		<xsl:variable name="codefile"
			select="document('../../../de.vogella.docbook/mystylesheets/headerstandalone.html',/)" />
		<xsl:copy-of select="$codefile/htmlcode/node()" />
	</xsl:template>
	<xsl:template name="user.footer.content">
		<xsl:variable name="codefile"
			select="document('../../../de.vogella.docbook/mystylesheets/footerstandalone.html',/)" />
		<xsl:copy-of select="$codefile/htmlcode/node()" />
	</xsl:template>
</xsl:stylesheet>