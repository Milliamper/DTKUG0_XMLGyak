<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output indent="yes"/>

    <xsl:template match="/">
		<autok>
			<xsl:for-each select="/autok/auto">
				<xsl:sort select="ar"/>
				<auto>
					<xsl:attribute name="rsz"><xsl:value-of select="@rsz"/></xsl:attribute>
					<ar><xsl:value-of select="ar"/></ar>
				</auto>
			</xsl:for-each>
		</autok>
    </xsl:template>
</xsl:stylesheet>