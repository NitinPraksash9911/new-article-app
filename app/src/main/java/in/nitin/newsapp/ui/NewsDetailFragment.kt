package `in`.nitin.newsapp.ui

import `in`.nitin.newsapp.R
import `in`.nitin.newsapp.databinding.FragmentNewsDetailBinding
import `in`.nitin.newsapp.ui.utils.hide
import `in`.nitin.newsapp.ui.utils.show
import `in`.nitin.newsapp.ui.utils.snack
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs

/**
 * A simple [Fragment] subclass.
 */
class NewsDetailFragment : Fragment() {

    private val args: NewsDetailFragmentArgs by navArgs()

    var string: String? = null
    lateinit var binding: FragmentNewsDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNewsDetailBinding.inflate(layoutInflater)

        setToolBar()
        setWebView()
        return binding.root
    }

    private fun setToolBar() {
        if (requireActivity() is MainActivity) {
            val mTitle = if (args.sourceName.isNotEmpty()) args.sourceName else "Detail News"
            (activity as MainActivity?)!!.setToolbar(mTitle, true)
        }

    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setWebView() {
        if (args.newsUrl.isNotEmpty()) {

            binding.webView.apply {
                settings.apply {
                    javaScriptEnabled = true
                    useWideViewPort = true
                    loadWithOverviewMode = true
                    setSupportMultipleWindows(true)
                }
                webViewClient = object : WebViewClient() {

                    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                        super.onPageStarted(view, url, favicon)
                        binding.statusImageView.show()
                    }

                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        binding.statusImageView.hide()


                    }

                    /* Handling web view error */
                    override fun onReceivedError(
                        view: WebView?,
                        request: WebResourceRequest?,
                        error: WebResourceError?
                    ) {
                        super.onReceivedError(view, request, error);
                        binding.statusImageView.hide()

                    }
                }
                loadUrl(args.newsUrl)
            }
        } else {
            getString(R.string.no_url).snack(Color.RED, binding.root)
        }
    }

}
