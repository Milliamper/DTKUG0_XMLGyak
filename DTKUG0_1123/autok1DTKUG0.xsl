<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<h2>Autok rendszamai ar szerint csokkeno sorrendben</h2>
			<ul>
				<xsl:for-each select="autok/auto">
					<xsl:sort select="ar"/>
					<li><xsl:value-of select="@rsz"/>, <xsl:value-of select="ar"/></li>
				</xsl:for-each>
			</ul>
		</html>
	</xsl:template>
</xsl:stylesheet>