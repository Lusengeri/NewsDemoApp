package com.alsoftware.newsdemoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.alsoftware.newsdemoapp.R

class WebFragment : Fragment() {
    private lateinit var myWebView : WebView
    private lateinit var webUrl : String
    private lateinit var progressBar : ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_web, container, false)
        myWebView = view.findViewById(R.id.myWebView)
        progressBar = view.findViewById(R.id.progressBar)
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
        myWebView.webViewClient = MyWebViewClient()
        myWebView.webChromeClient= MyChromeClient()
        myWebView.settings.javaScriptEnabled=true
        myWebView.loadUrl(url)
    }

    inner class MyWebViewClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return false
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            progressBar.visibility = View.GONE
        }
    }

    inner class MyChromeClient : WebChromeClient() {
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            if (view != null) {
                progressBar.progress = newProgress
            }
        }
    }
}