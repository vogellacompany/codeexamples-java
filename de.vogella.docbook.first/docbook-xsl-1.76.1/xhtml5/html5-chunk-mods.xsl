<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet 
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
  xmlns:exsl="http://exslt.org/common"
  xmlns="http://www.w3.org/1999/xhtml"
  exclude-result-prefixes="exsl"
  version="1.0">

<!-- $Id: html5-chunk-mods.xsl,v 1.1 2011-09-16 21:44:00 bobs Exp $ -->

<!-- Fix bug: original called "generate.css" -->
<xsl:template match="*" mode="process.root" priority="1">
  <xsl:apply-templates select="."/>
  <xsl:call-template name="generate.css.files"/>
</xsl:template>

<!-- HTML5: Modify to wrap header in HTML5 <header> element. -->
<xsl:template name="header.navigation">
  <xsl:param name="prev" select="/foo"/>
  <xsl:param name="next" select="/foo"/>
  <xsl:param name="nav.context"/>

  <xsl:variable name="home" select="/*[1]"/>
  <xsl:variable name="up" select="parent::*"/>

  <xsl:variable name="row1" select="$navig.showtitles != 0"/>
  <xsl:variable name="row2" select="count($prev) &gt; 0
                                    or (count($up) &gt; 0 
                                        and generate-id($up) != generate-id($home)
                                        and $navig.showtitles != 0)
                                    or count($next) &gt; 0"/>

  <xsl:if test="$suppress.navigation = '0' and $suppress.header.navigation = '0'">
    <xsl:variable name="content">
      <header>
        <div class="navheader">
          <xsl:if test="$row1 or $row2">
            <table width="100%" summary="Navigation header">
              <xsl:if test="$row1">
                <tr>
                  <th colspan="3" align="center">
                    <xsl:apply-templates select="." mode="object.title.markup"/>
                  </th>
                </tr>
              </xsl:if>
    
              <xsl:if test="$row2">
                <tr>
                  <td width="20%" align="{$direction.align.start}">
                    <xsl:if test="count($prev)>0">
                      <a accesskey="p">
                        <xsl:attribute name="href">
                          <xsl:call-template name="href.target">
                            <xsl:with-param name="object" select="$prev"/>
                          </xsl:call-template>
                        </xsl:attribute>
                        <xsl:call-template name="navig.content">
                          <xsl:with-param name="direction" select="'prev'"/>
                        </xsl:call-template>
                      </a>
                    </xsl:if>
                    <xsl:text>&#160;</xsl:text>
                  </td>
                  <th width="60%" align="center">
                    <xsl:choose>
                      <xsl:when test="count($up) > 0
                                      and generate-id($up) != generate-id($home)
                                      and $navig.showtitles != 0">
                        <xsl:apply-templates select="$up" mode="object.title.markup"/>
                      </xsl:when>
                      <xsl:otherwise>&#160;</xsl:otherwise>
                    </xsl:choose>
                  </th>
                  <td width="20%" align="{$direction.align.end}">
                    <xsl:text>&#160;</xsl:text>
                    <xsl:if test="count($next)>0">
                      <a accesskey="n">
                        <xsl:attribute name="href">
                          <xsl:call-template name="href.target">
                            <xsl:with-param name="object" select="$next"/>
                          </xsl:call-template>
                        </xsl:attribute>
                        <xsl:call-template name="navig.content">
                          <xsl:with-param name="direction" select="'next'"/>
                        </xsl:call-template>
                      </a>
                    </xsl:if>
                  </td>
                </tr>
              </xsl:if>
            </table>
          </xsl:if>
          <xsl:if test="$header.rule != 0">
            <hr/>
          </xsl:if>
        </div>
      </header>
    </xsl:variable>

    <!-- And fix up any style atts -->
    <xsl:call-template name="convert.styles">
      <xsl:with-param name="content" select="$content"/>
    </xsl:call-template>

  </xsl:if>
</xsl:template>


<!-- HTML5: Modify to wrap footer in HTML5 <footer> element. -->
<xsl:template name="footer.navigation">
  <xsl:param name="prev" select="/foo"/>
  <xsl:param name="next" select="/foo"/>
  <xsl:param name="nav.context"/>

  <xsl:variable name="home" select="/*[1]"/>
  <xsl:variable name="up" select="parent::*"/>

  <xsl:variable name="row1" select="count($prev) &gt; 0
                                    or count($up) &gt; 0
                                    or count($next) &gt; 0"/>

  <xsl:variable name="row2" select="($prev and $navig.showtitles != 0)
                                    or (generate-id($home) != generate-id(.)
                                        or $nav.context = 'toc')
                                    or ($chunk.tocs.and.lots != 0
                                        and $nav.context != 'toc')
                                    or ($next and $navig.showtitles != 0)"/>

  <xsl:if test="$suppress.navigation = '0' and $suppress.footer.navigation = '0'">
    <xsl:variable name="content">
      <footer>
        <div class="navfooter">
          <xsl:if test="$footer.rule != 0">
            <hr/>
          </xsl:if>
    
          <xsl:if test="$row1 or $row2">
            <table width="100%" summary="Navigation footer">
              <xsl:if test="$row1">
                <tr>
                  <td width="40%" align="{$direction.align.start}">
                    <xsl:if test="count($prev)>0">
                      <a accesskey="p">
                        <xsl:attribute name="href">
                          <xsl:call-template name="href.target">
                            <xsl:with-param name="object" select="$prev"/>
                          </xsl:call-template>
                        </xsl:attribute>
                        <xsl:call-template name="navig.content">
                          <xsl:with-param name="direction" select="'prev'"/>
                        </xsl:call-template>
                      </a>
                    </xsl:if>
                    <xsl:text>&#160;</xsl:text>
                  </td>
                  <td width="20%" align="center">
                    <xsl:choose>
                      <xsl:when test="count($up)&gt;0
                                      and generate-id($up) != generate-id($home)">
                        <a accesskey="u">
                          <xsl:attribute name="href">
                            <xsl:call-template name="href.target">
                              <xsl:with-param name="object" select="$up"/>
                            </xsl:call-template>
                          </xsl:attribute>
                          <xsl:call-template name="navig.content">
                            <xsl:with-param name="direction" select="'up'"/>
                          </xsl:call-template>
                        </a>
                      </xsl:when>
                      <xsl:otherwise>&#160;</xsl:otherwise>
                    </xsl:choose>
                  </td>
                  <td width="40%" align="{$direction.align.end}">
                    <xsl:text>&#160;</xsl:text>
                    <xsl:if test="count($next)>0">
                      <a accesskey="n">
                        <xsl:attribute name="href">
                          <xsl:call-template name="href.target">
                            <xsl:with-param name="object" select="$next"/>
                          </xsl:call-template>
                        </xsl:attribute>
                        <xsl:call-template name="navig.content">
                          <xsl:with-param name="direction" select="'next'"/>
                        </xsl:call-template>
                      </a>
                    </xsl:if>
                  </td>
                </tr>
              </xsl:if>
    
              <xsl:if test="$row2">
                <tr>
                  <td width="40%" align="{$direction.align.start}" valign="top">
                    <xsl:if test="$navig.showtitles != 0">
                      <xsl:apply-templates select="$prev" mode="object.title.markup"/>
                    </xsl:if>
                    <xsl:text>&#160;</xsl:text>
                  </td>
                  <td width="20%" align="center">
                    <xsl:choose>
                      <xsl:when test="$home != . or $nav.context = 'toc'">
                        <a accesskey="h">
                          <xsl:attribute name="href">
                            <xsl:call-template name="href.target">
                              <xsl:with-param name="object" select="$home"/>
                            </xsl:call-template>
                          </xsl:attribute>
                          <xsl:call-template name="navig.content">
                            <xsl:with-param name="direction" select="'home'"/>
                          </xsl:call-template>
                        </a>
                        <xsl:if test="$chunk.tocs.and.lots != 0 and $nav.context != 'toc'">
                          <xsl:text>&#160;|&#160;</xsl:text>
                        </xsl:if>
                      </xsl:when>
                      <xsl:otherwise>&#160;</xsl:otherwise>
                    </xsl:choose>
    
                    <xsl:if test="$chunk.tocs.and.lots != 0 and $nav.context != 'toc'">
                      <a accesskey="t">
                        <xsl:attribute name="href">
                          <xsl:apply-templates select="/*[1]"
                                               mode="recursive-chunk-filename">
                            <xsl:with-param name="recursive" select="true()"/>
                          </xsl:apply-templates>
                          <xsl:text>-toc</xsl:text>
                          <xsl:value-of select="$html.ext"/>
                        </xsl:attribute>
                        <xsl:call-template name="gentext">
                          <xsl:with-param name="key" select="'nav-toc'"/>
                        </xsl:call-template>
                      </a>
                    </xsl:if>
                  </td>
                  <td width="40%" align="{$direction.align.end}" valign="top">
                    <xsl:text>&#160;</xsl:text>
                    <xsl:if test="$navig.showtitles != 0">
                      <xsl:apply-templates select="$next" mode="object.title.markup"/>
                    </xsl:if>
                  </td>
                </tr>
              </xsl:if>
            </table>
          </xsl:if>
        </div>
      </footer>
    </xsl:variable>

    <!-- And fix up any style atts -->
    <xsl:call-template name="convert.styles">
      <xsl:with-param name="content" select="$content"/>
    </xsl:call-template>
  </xsl:if>
</xsl:template>

<!-- HTML5: fix styles in footnote output -->
<xsl:template name="process.footnotes">
  <xsl:variable name="footnotes" select=".//footnote"/>
  <xsl:variable name="fcount">
    <xsl:call-template name="count.footnotes.in.this.chunk">
      <xsl:with-param name="node" select="."/>
      <xsl:with-param name="footnotes" select="$footnotes"/>
    </xsl:call-template>
  </xsl:variable>

<!--
  <xsl:message>
    <xsl:value-of select="name(.)"/>
    <xsl:text> fcount: </xsl:text>
    <xsl:value-of select="$fcount"/>
  </xsl:message>
-->

  <!-- Only bother to do this if there's at least one non-table footnote -->
  <xsl:if test="$fcount &gt; 0">
    <div class="footnotes">
      <xsl:call-template name="footnotes.attributes"/>
      <br/>
      <hr style="width: 100; align: {$direction.align.start};"/>
      <xsl:call-template name="process.footnotes.in.this.chunk">
        <xsl:with-param name="node" select="."/>
        <xsl:with-param name="footnotes" select="$footnotes"/>
      </xsl:call-template>
    </div>
  </xsl:if>

  <!-- FIXME: When chunking, only the annotations actually used
              in this chunk should be referenced. I don't think it
              does any harm to reference them all, but it adds
              unnecessary bloat to each chunk. -->
  <xsl:if test="$annotation.support != 0 and //annotation">
    <div class="annotation-list">
      <div class="annotation-nocss">
        <p>The following annotations are from this essay. You are seeing
        them here because your browser doesn’t support the user-interface
        techniques used to make them appear as ‘popups’ on modern browsers.</p>
      </div>

      <xsl:apply-templates select="//annotation"
                           mode="annotation-popup"/>
    </div>
  </xsl:if>
</xsl:template>

<!-- HTML5: link rel="home" is not permitted -->
<xsl:template name="html.head">
  <xsl:param name="prev" select="/foo"/>
  <xsl:param name="next" select="/foo"/>
  <xsl:variable name="this" select="."/>
  <xsl:variable name="home" select="/*[1]"/>
  <xsl:variable name="up" select="parent::*"/>

  <head>
    <xsl:call-template name="system.head.content"/>
    <xsl:call-template name="head.content"/>

    <!--
    <xsl:if test="$home">
      <link rel="home">
        <xsl:attribute name="href">
          <xsl:call-template name="href.target">
            <xsl:with-param name="object" select="$home"/>
          </xsl:call-template>
        </xsl:attribute>
        <xsl:attribute name="title">
          <xsl:apply-templates select="$home"
                               mode="object.title.markup.textonly"/>
        </xsl:attribute>
      </link>
    </xsl:if>
    -->

    <xsl:if test="$up">
      <link rel="up">
        <xsl:attribute name="href">
          <xsl:call-template name="href.target">
            <xsl:with-param name="object" select="$up"/>
          </xsl:call-template>
        </xsl:attribute>
        <xsl:attribute name="title">
          <xsl:apply-templates select="$up" mode="object.title.markup.textonly"/>
        </xsl:attribute>
      </link>
    </xsl:if>

    <xsl:if test="$prev">
      <link rel="prev">
        <xsl:attribute name="href">
          <xsl:call-template name="href.target">
            <xsl:with-param name="object" select="$prev"/>
          </xsl:call-template>
        </xsl:attribute>
        <xsl:attribute name="title">
          <xsl:apply-templates select="$prev" mode="object.title.markup.textonly"/>
        </xsl:attribute>
      </link>
    </xsl:if>

    <xsl:if test="$next">
      <link rel="next">
        <xsl:attribute name="href">
          <xsl:call-template name="href.target">
            <xsl:with-param name="object" select="$next"/>
          </xsl:call-template>
        </xsl:attribute>
        <xsl:attribute name="title">
          <xsl:apply-templates select="$next" mode="object.title.markup.textonly"/>
        </xsl:attribute>
      </link>
    </xsl:if>

    <xsl:if test="$html.extra.head.links != 0">
      <xsl:for-each select="//part
                            |//reference
                            |//preface
                            |//chapter
                            |//article
                            |//refentry
                            |//appendix[not(parent::article)]|appendix
                            |//glossary[not(parent::article)]|glossary
                            |//index[not(parent::article)]|index">
        <link rel="{local-name(.)}">
          <xsl:attribute name="href">
            <xsl:call-template name="href.target">
              <xsl:with-param name="context" select="$this"/>
              <xsl:with-param name="object" select="."/>
            </xsl:call-template>
          </xsl:attribute>
          <xsl:attribute name="title">
            <xsl:apply-templates select="." mode="object.title.markup.textonly"/>
          </xsl:attribute>
        </link>
      </xsl:for-each>

      <xsl:for-each select="section|sect1|refsection|refsect1">
        <link>
          <xsl:attribute name="rel">
            <xsl:choose>
              <xsl:when test="local-name($this) = 'section'
                              or local-name($this) = 'refsection'">
                <xsl:value-of select="'subsection'"/>
              </xsl:when>
              <xsl:otherwise>
                <xsl:value-of select="'section'"/>
              </xsl:otherwise>
            </xsl:choose>
          </xsl:attribute>
          <xsl:attribute name="href">
            <xsl:call-template name="href.target">
              <xsl:with-param name="context" select="$this"/>
              <xsl:with-param name="object" select="."/>
            </xsl:call-template>
          </xsl:attribute>
          <xsl:attribute name="title">
            <xsl:apply-templates select="." mode="object.title.markup.textonly"/>
          </xsl:attribute>
        </link>
      </xsl:for-each>

      <xsl:for-each select="sect2|sect3|sect4|sect5|refsect2|refsect3">
        <link rel="subsection">
          <xsl:attribute name="href">
            <xsl:call-template name="href.target">
              <xsl:with-param name="context" select="$this"/>
              <xsl:with-param name="object" select="."/>
            </xsl:call-template>
          </xsl:attribute>
          <xsl:attribute name="title">
            <xsl:apply-templates select="." mode="object.title.markup.textonly"/>
          </xsl:attribute>
        </link>
      </xsl:for-each>
    </xsl:if>

    <!-- * if we have a legalnotice and user wants it output as a -->
    <!-- * separate page and $html.head.legalnotice.link.types is -->
    <!-- * non-empty, we generate a link or links for each value in -->
    <!-- * $html.head.legalnotice.link.types -->
    <xsl:if test="//legalnotice
                  and not($generate.legalnotice.link = 0)
                  and not($html.head.legalnotice.link.types = '')">
      <xsl:call-template name="make.legalnotice.head.links"/>
    </xsl:if>

    <xsl:call-template name="user.head.content"/>
  </head>
</xsl:template>

<!-- Add call to new root.attributes template for <html> attribute -->
<xsl:template name="chunk-element-content">
  <xsl:param name="prev"/>
  <xsl:param name="next"/>
  <xsl:param name="nav.context"/>
  <xsl:param name="content">
    <xsl:apply-imports/>
  </xsl:param>

  <xsl:call-template name="user.preroot"/>

  <html>
    <xsl:call-template name="root.attributes"/>
    <xsl:call-template name="html.head">
      <xsl:with-param name="prev" select="$prev"/>
      <xsl:with-param name="next" select="$next"/>
    </xsl:call-template>

    <body>
      <xsl:call-template name="body.attributes"/>
      <xsl:call-template name="user.header.navigation"/>

      <xsl:call-template name="header.navigation">
        <xsl:with-param name="prev" select="$prev"/>
        <xsl:with-param name="next" select="$next"/>
        <xsl:with-param name="nav.context" select="$nav.context"/>
      </xsl:call-template>

      <xsl:call-template name="user.header.content"/>

      <xsl:copy-of select="$content"/>

      <xsl:call-template name="user.footer.content"/>

      <xsl:call-template name="footer.navigation">
        <xsl:with-param name="prev" select="$prev"/>
        <xsl:with-param name="next" select="$next"/>
        <xsl:with-param name="nav.context" select="$nav.context"/>
      </xsl:call-template>

      <xsl:call-template name="user.footer.navigation"/>
    </body>
  </html>
  <xsl:value-of select="$chunk.append"/>
</xsl:template>


</xsl:stylesheet>
