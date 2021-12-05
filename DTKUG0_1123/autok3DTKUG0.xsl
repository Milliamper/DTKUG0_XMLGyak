<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <body>
                <table>
                    <tr>
                        <th>Ennyi elembol all a dokumentum</th>
                    </tr>
                    <tr>
                        <td><xsl:value-of select="count(autok/auto)"/></td>
                    </tr>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>