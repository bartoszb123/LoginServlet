<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <!--<xsl:output method="html" version="1.0"-->
                <!--encoding="UTF-8" indent="yes"/>-->

    <xsl:template match="/">
        <html>
            <body>
                <h2>My Documents</h2>
                <!--<link rel="stylesheet" href="/loginservlet/styles/tableXSL-styles.css"/>-->
                <table border="1" class="tableXSL">
                    <tr bgcolor="#9acd32">

                        <th>nr</th>
                        <th>id</th>
                        <th>name</th>
                        <th>content</th>
                        <th>alias</th>
                    </tr>
                    <xsl:for-each select="documents/document">
                        <!--SORTOWANIE-->
                        <!--<xsl:sort select="alias"/>-->
                        <!--<xsl:sort select="name" data-type="text" order="descending"/>-->
                        <!--<xsl:sort select="id" data-type="number" order="descending"/>-->
                        <tr>

                            <xsl:attribute name="BGCOLOR">
                                <xsl:choose>
                                    <xsl:when test="alias='11'">green</xsl:when>
                                    <xsl:when test="alias='1111'">yellow</xsl:when>
                                    <xsl:otherwise>white</xsl:otherwise>
                                </xsl:choose>
                            </xsl:attribute>

                            <!--<TD><xsl:number/></TD>    -> Automatyczne zliczanie/dodanie nowej kolumny-->
                            <TD>
                                <xsl:number/>
                            </TD>
                            <td>
                                <xsl:value-of select="id"/>
                            </td>
                            <td>
                                <xsl:value-of select="name"/>
                            </td>
                            <td>
                                <xsl:value-of select="content" disable-output-escaping="yes"/>
                            </td>
                            <td>
                                <xsl:value-of select="alias"/>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>

                <!--<h3>Dokumenty o aliasie 11:</h3>-->
                <!--<xsl:for-each select="alias[@alias=11]">-->
                <!--name: <xsl:value-of select="name"/> <br />-->
                <!--</xsl:for-each>-->
                <!--<hr/>-->
                <a class="btn" href="info">GoBack</a>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>