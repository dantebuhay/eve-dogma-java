<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output
            method="text"
            omit-xml-declaration="yes"
            indent="no"
            encoding="UTF-8"/>

    <xsl:template match="hardware">
        {
            "slot": "<xsl:value-of select="@slot"/>",
            "type": "<xsl:value-of select="@type"/>",
            "quantity": "<xsl:value-of select="@qty"/>"
        }
        <xsl:choose>
            <xsl:when test="position() != last()">,</xsl:when>
        </xsl:choose>
    </xsl:template>

    <xsl:template match="fitting">
        {
            "name": "<xsl:value-of select="@name"/>",
            "description": "<xsl:value-of select="description/@value"/>",
            "shipType": "<xsl:value-of select="shipType/@value"/>",
            "hardware": [<xsl:apply-templates select="./hardware"/>]
        }
        <xsl:choose>
            <xsl:when test="position() != last()">,</xsl:when>
        </xsl:choose>
    </xsl:template>

    <xsl:template match="fittings">
        "fittings": [<xsl:apply-templates select="./fitting"/>]
    </xsl:template>

    <xsl:template match="/">
        {
            <xsl:apply-templates select="fittings"/>
        }
    </xsl:template>
</xsl:stylesheet>