<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet 
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
  xmlns:exsl="http://exslt.org/common"
  xmlns="http://www.w3.org/1999/xhtml"
  xmlns:stbl="http://nwalsh.com/xslt/ext/com.nwalsh.saxon.Table"
  xmlns:xtbl="xalan://com.nwalsh.xalan.Table"
  xmlns:lxslt="http://xml.apache.org/xslt"
  xmlns:ptbl="http://nwalsh.com/xslt/ext/xsltproc/python/Table"
  exclude-result-prefixes="exsl stbl xtbl lxslt ptbl"
  version="1.0">

<!-- $Id: docbook.xsl,v 1.1 2011-09-16 21:44:00 bobs Exp $ -->
<xsl:import href="xhtml-docbook.xsl"/>
<xsl:include href="html5-element-mods.xsl"/>

<xsl:output method="xml" encoding="UTF-8" />

</xsl:stylesheet>
