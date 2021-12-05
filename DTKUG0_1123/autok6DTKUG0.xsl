<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<html lang="hu">
			<h2>Varosonkent az autok osszara es darabszama</h2>
			<ul>
				<xsl:for-each-group select="/autok/auto" group-by="tulaj/varos">
					<li>
						<xsl:value-of select="current-grouping-key()"/>
						<ul>
							<li><xsl:value-of select="count(current-group())"/> db autó</li>
							<li>Átlagáruk: <xsl:value-of select="avg(current-group()/ar)"/></li>
						</ul>
					</li>
				</xsl:for-each-group>
			</ul>
		</html>
	</xsl:template>
</xsl:stylesheet>