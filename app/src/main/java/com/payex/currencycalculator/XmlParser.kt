package com.payex.currencycalculator

import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream

class XmlParser {
    private val obsList = ArrayList<Obs>()
    private var obs: Obs? = null
    private var text: String? = null

    fun parse(inputStream: InputStream): List<Obs> {
        try {
            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            val parser = factory.newPullParser()
            parser.setInput(inputStream, null)
            var eventType = parser.eventType
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagname = parser.name
                when (eventType) {
                    XmlPullParser.START_TAG -> if (tagname.equals("generic:Obs", ignoreCase = true)) {
                        obs = Obs()
                    }
                    XmlPullParser.TEXT -> text = parser.text
                    XmlPullParser.END_TAG -> if (tagname.equals("generic:Obs", ignoreCase = true)) {
                        // add employee object to list
                        obs?.let { obsList.add(it) }
                    } else if (tagname.equals("generic:ObsDimension", ignoreCase = true)) {
                        obs!!.obsDimension = text
                    } else if (tagname.equals("generic:ObsValue", ignoreCase = true)) {
                        obs!!.obsValue = text?.toDouble()!!
                    }

                    else -> {
                    }
                }
                eventType = parser.next()
            }

        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        println("printing list")
        obsList.map { println(it) }
        return obsList
    }
}