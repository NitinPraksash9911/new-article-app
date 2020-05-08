package `in`.nitin.newsapp.ui

import `in`.nitin.newsapp.viewmodel.ViewModelProviderFactory
import `in`.nitin.newsapp.R
import `in`.nitin.newsapp.application.NewsApplication
import `in`.nitin.newsapp.databinding.FragmentNewsListBinding
import `in`.nitin.newsapp.datasource.helper.Result
import `in`.nitin.newsapp.datasource.roomDb.entity.Article
import `in`.nitin.newsapp.ui.adapter.NewsListAdapter
import `in`.nitin.newsapp.ui.utils.snack
import `in`.nitin.newsapp.viewmodel.NewsDataViewModel
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import javax.inject.Inject

/**
 *  [NewsListFragment] class.
 */
class NewsListFragment : Fragment(), NewsListAdapter.ClickListener {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    lateinit var mAdapter: NewsListAdapter

    lateinit var binding: FragmentNewsListBinding


    lateinit var newsDataViewModel: NewsDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNewsListBinding.inflate(layoutInflater)

        setToolBar()
        /* dependency injection*/
        NewsApplication.getComponent()!!.inject(this)

        initView()
        subscribeData()
        return binding.root

    }
    private fun setToolBar() {
        if (requireActivity() is MainActivity) {
            (activity as MainActivity?)!!.setToolbar(this.javaClass.simpleName, false)
        }
    }

    private fun initView() {
        mAdapter = NewsListAdapter(this)
        newsDataViewModel =
            ViewModelProvider(this, providerFactory).get(NewsDataViewModel::class.java)
        binding.apply {
            // Allow Data Binding to Observe LiveData with the lifecycle of Fragment or Activity
            this.lifecycleOwner = this@NewsListFragment
            rv.adapter = mAdapter
        }

    }

    private fun subscribeData() {
        newsDataViewModel.noteListdata.observe(viewLifecycleOwner, Observer {

            when (it.status) {
                Result.Status.LOADING -> {
                }
                Result.Status.SUCCESS -> {
                    mAdapter.submitList(it.data)
                }
                Result.Status.ERROR -> {
                    /*Shows snack bar on error*/
                    it.message!!.snack(Color.RED, binding.root)

                }
            }
        })
    }
/**
 * RecyclerView item click listener
 * */
    override fun onItemClick(article: Article) {
        val action =
            article.source!!.name?.let {
                NewsListFragmentDirections.actionNewsListFragmentToNewsDetailFragment(
                    article.url!!,
                    it
                )
            }

        if (NewsApplication.getNetworkStatus()) {
            findNavController().navigate(action!!)
        } else {
            getString(R.string.no_internet).snack(Color.DKGRAY, binding.root)
        }
    }


}
