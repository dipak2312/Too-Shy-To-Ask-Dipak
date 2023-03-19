package com.example.tooshytoask.activity.story


import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.SparseIntArray
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.app.quicklook.commonUtils.utility.storyUtil.CubeOutTransformer
import com.app.quicklook.view.story.pager.PageChangeListener
import com.app.quicklook.view.story.pager.PageViewOperator
import com.bumptech.glide.Glide
import com.example.tooshytoask.API.WebServiceModel
import com.example.tooshytoask.adapters.StoryPagerAdapter
import com.example.tooshytoask.AuthModels.StoryAuthModel
import com.example.tooshytoask.Models.StoryDetails
import com.example.tooshytoask.Models.StoryResponse
import com.example.tooshytoask.R
import com.example.tooshytoask.Utils.CustomProgressDialog
import com.example.tooshytoask.databinding.ActivityStoryInfoBinding

import com.google.android.exoplayer2.upstream.DataSpec
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async


class StoryInfoActivity : AppCompatActivity(), PageViewOperator {
    var binding: ActivityStoryInfoBinding? = null
    private lateinit var pagerAdapter: StoryPagerAdapter
    private var currentPage: Int = 0
    var postId:String?=""
    var imageName:String?=""
    var story_id:String? = ""
    var getAllStories=ArrayList<StoryDetails>()
    var status:String?=""
    var adapter: ArrayAdapter<String>? = null
    var reportType: String? = ""
    var viewAs:String?=""
    var dialog:CustomProgressDialog?=null
    var storyDetails: java.util.ArrayList<StoryDetails>? = null

    companion object {
        val progressState = SparseIntArray()
        fun getStartIntent(
            context: Context,
            storyId:String
        ): Intent {
            return Intent(context, StoryInfoActivity::class.java).apply {

                putExtra("story_id",storyId)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // firebaseAnalytics = Firebase.analytics
        binding = DataBindingUtil.setContentView(this,R.layout.activity_story_info)
        dialog = CustomProgressDialog(this@StoryInfoActivity)

        story_id=intent?.getStringExtra("story_id")
//        val intent = intent
//        if (intent != null) {
//            story_id = intent.getStringExtra("story_id")
//        }

        storyDetails= ArrayList()
        getStory()
    }



    fun getStory() {
        dialog!!.show("")
        dialog!!.dismiss("")
        val model = StoryAuthModel()
        model.category_id = story_id
        WebServiceModel.getRestApi().getStory(model)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<StoryResponse?>() {
                override fun onNext(storyResponse: StoryResponse) {
                    val msg = storyResponse.msg
                    if (msg == "success") {
                        storyDetails = storyResponse.storyDetails
                        if(storyDetails != null)
                        {
                            setUpPager(storyDetails!!)
                        }
                    }
                }

                override fun onError(e: Throwable) {}
                override fun onComplete() {}
            })
    }

    private fun setUpPager(storyDetails: java.util.ArrayList<StoryDetails>) {

        val StoryDetailsList = storyDetails
        preLoadStories(StoryDetailsList)

        pagerAdapter = StoryPagerAdapter(
            supportFragmentManager,
            StoryDetailsList
        )
         binding!!.storyViewPager.adapter = pagerAdapter
         binding!!.storyViewPager.currentItem = currentPage
         binding!!.storyViewPager.setPageTransformer(
            true,
            CubeOutTransformer()
        )
         binding!!.storyViewPager.addOnPageChangeListener(object : PageChangeListener() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                currentPage = position
            }

            override fun onPageScrollCanceled() {
               // currentFragment()?.resumeCurrentStory()
            }
        })
    }

    private fun preLoadStories(StoryDetailsList: ArrayList<StoryDetails>) {
        val imageList = mutableListOf<String>()
        val videoList = mutableListOf<String>()

        StoryDetailsList.forEach { StoryDetails ->

//            StoryDetails.forEach { story ->
//                if (story.isVideo()) {
//                    videoList.add(story.url)
//                } else {
//                    imageList.add(story.url)
//                }
//            }
        }
        preLoadVideos(videoList)
        preLoadImages(imageList)
    }

    private fun preLoadVideos(videoList: MutableList<String>) {
        videoList.map { data ->
            GlobalScope.async {
                val dataUri = Uri.parse(data)
                val dataSpec = DataSpec(dataUri, 0, (500 * 1024).toLong(), null)
                val dataSource: DefaultDataSource =
                    DefaultDataSourceFactory(
                        applicationContext,
                        Util.getUserAgent(applicationContext, getString(R.string.app_name))
                    ).createDataSource()

//                val listener =
//                    CacheUtil.ProgressListener { requestLength: Long, bytesCached: Long, _: Long ->
//                        val downloadPercentage = (bytesCached * 100.0
//                                / requestLength)
//                        Log.d("preLoadVideos", "downloadPercentage: $downloadPercentage")
//                    }

//                try {
//                    CacheUtil.cache(
//                        dataSpec,
//                        AppineersApplication.simpleCache,
//                        CacheUtil.DEFAULT_CACHE_KEY_FACTORY,
//                        dataSource,
//                        listener,
//                        null
//                    )
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
            }
        }
    }

    private fun preLoadImages(imageList: MutableList<String>) {
        imageList.forEach { imageStory ->
            Glide.with(this).load(imageStory).preload()
        }
    }

    private fun currentFragment(): StoryDisplayFragment? {
        return pagerAdapter.findFragmentByPosition( binding!!.storyViewPager, currentPage) as StoryDisplayFragment
    }

    /**
     * Change ViewPage sliding programmatically(not using reflection).
     * https://tech.dely.jp/entry/2018/12/13/110000
     * What for?
     * setCurrentItem(int, boolean) changes too fast. And it cannot set animation duration.
     */
    private var prevDragPosition = 0

    private fun fakeDrag(forward: Boolean) {
        if (prevDragPosition == 0 &&  binding!!.storyViewPager.beginFakeDrag()) {
            ValueAnimator.ofInt(0,  binding!!.storyViewPager.width).apply {
                duration = 400L
                interpolator = FastOutSlowInInterpolator()
                addListener(object : Animator.AnimatorListener {

                    override fun onAnimationStart(animation: Animator) {
                        //TODO("Not yet implemented")
                    }

                    override fun onAnimationEnd(animation: Animator) {
                        removeAllUpdateListeners()
                        if ( binding!!.storyViewPager.isFakeDragging) {
                            binding!!.storyViewPager.endFakeDrag()
                        }
                        prevDragPosition = 0
                    }

                    override fun onAnimationCancel(animation: Animator) {
                        removeAllUpdateListeners()
                        if ( binding!!.storyViewPager.isFakeDragging) {
                            binding!!.storyViewPager.endFakeDrag()
                        }
                        prevDragPosition = 0
                    }

                    override fun onAnimationRepeat(animation: Animator) {
                        //TODO("Not yet implemented")
                    }
                })
                addUpdateListener {
                    if (! binding!!.storyViewPager.isFakeDragging) return@addUpdateListener
                    val dragPosition: Int = it.animatedValue as Int
                    val dragOffset: Float =
                        ((dragPosition - prevDragPosition) * if (forward) -1 else 1).toFloat()
                    prevDragPosition = dragPosition
                     binding!!.storyViewPager.fakeDragBy(dragOffset)
                }
            }.start()
        }
    }




    override fun backPageView() {
        if ( binding!!.storyViewPager.currentItem > 0) {
            try {
                fakeDrag(false)
            } catch (e: Exception) {
                //NO OP
            }
        }
    }

    override fun nextPageView() {
        if ( binding!!.storyViewPager.currentItem + 1 <  binding!!.storyViewPager.adapter?.count ?: 0) {
            try {
                fakeDrag(true)
            } catch (e: Exception) {
                //NO OP
            }
        } else {
            //there is no next story
            //var postIds=(application as AppineersApplication).viewPostId.clear()
            finish()
            //Toast.makeText(this, "All stories displayed.", Toast.LENGTH_LONG).show()
        }
    }

}



