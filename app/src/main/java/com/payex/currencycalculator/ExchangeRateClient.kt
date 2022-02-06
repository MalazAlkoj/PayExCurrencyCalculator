package com.payex.currencycalculator

import android.util.Xml
import okhttp3.*
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
import java.io.InputStream
import java.net.URL

class ExchangeRateClient(private val client: OkHttpClient = OkHttpClient()) {
    // TODO fiksme, for en eller annen grunn, blir resultatet printed, men klarer ikke å få den ut
    // TODO den skulle bli sendt videre til XMLParser,
    fun run(url: String):InputStream? {
        val request = Request.Builder()
            .url(url)
            .build()
        var responseAsList: List<*>
        var responseStream: InputStream?=null
        var responseBody: ResponseBody?=null
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
                println(response.body?.string())
                responseStream = if (response.isSuccessful)
                    response.body?.byteStream()
                else
                    null
                responseBody=response.body
            }

        })
        println("Im responsebode")
        println(responseBody)
        return responseStream
    }

/*    @Throws(XmlPullParserException::class, IOException::class)
    fun parse(inputStream: InputStream): List<*> {
        inputStream.use { inputStream ->
            val parser: XmlPullParser = Xml.newPullParser()
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
            parser.setInput(inputStream, null)
            parser.nextTag()
            return readFeed(parser)
        }
    }*/
}