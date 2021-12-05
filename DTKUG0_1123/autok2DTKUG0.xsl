<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <body>
                <table>
                    <tr>
                        <th>Ennyi auto dragabb mint 30.000</th>
                    </tr>
                    <tr>
                        <td><xsl:value-of select="count(autok/auto/ar[. > 30000])"/></td>
                    </tr>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>