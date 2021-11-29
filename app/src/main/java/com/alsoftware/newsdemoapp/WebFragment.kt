package com.alsoftware.newsdemoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient

class WebFragment : Fragment() {
    private lateinit var myWebView : WebView
    private lateinit var webUrl : String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_web, container, false)
        myWebView = view.findViewById(R.id.myWebView)
        return view
    }

    override fun onStart() {
        super.onStart()

        // Capture the URL passed into the fragment & load web-page
        arguments?. let {
            var args = WebFragmentArgs.fromBundle(it)
            webUrl = args.websiteUrl
        }

        loadWebPage(webUrl)
    }

    private fun loadWebPage(url:String) {
        myWebView.webViewClient = WebViewClient()
        myWebView.webChromeClient= WebChromeClient()
        myWebView.settings.javaScriptEnabled=true
        myWebView.loadUrl(url)
    }
}