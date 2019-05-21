package com.mtn.imagebanner.imageslider

import android.content.Context
import android.os.Handler
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import com.mtn.imagebanner.ViewPagerAdapter
import com.mtn.imagebanner.ItemClickListener
import com.mtn.imagebanner.ImageBannerModel
import com.mtn.imagebanner.R
import java.util.*


class ImageBanner @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : RelativeLayout(context, attrs, defStyleAttr) {

    private var viewPager: ViewPager? = null
    private var pagerDots: LinearLayout? = null
    private var viewPagerAdapter: ViewPagerAdapter? = null

    private var dots: Array<ImageView?>? = null

    private var currentPage = 0
    private var imageCount = 0

    private var cornerRadius: Int = 0
    private var margin : Int = 0
    private var period: Long = 0
    private var delay: Long = 0
    private var autoCycle = false

    private var selectedDot = 0
    private var unselectedDot = 0
    private var errorImage = 0
    private var placeholder = 0

    init{
        LayoutInflater.from(getContext()).inflate(R.layout.image_slider, this, true)
        viewPager = findViewById(R.id.view_pager)
        pagerDots = findViewById(R.id.pager_dots)

        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.ImageBanner, defStyleAttr, defStyleAttr)

        cornerRadius = typedArray.getInt(R.styleable.ImageBanner_corner_radius, 0)
        period = typedArray.getInt(R.styleable.ImageBanner_period, 1000).toLong()
        delay = typedArray.getInt(R.styleable.ImageBanner_delay, 1000).toLong()
        autoCycle = typedArray.getBoolean(R.styleable.ImageBanner_auto_cycle, false)
        placeholder = typedArray.getResourceId(R.styleable.ImageBanner_placeholder, R.drawable.placeholder)
        selectedDot = typedArray.getResourceId(R.styleable.ImageBanner_selected_dot, R.drawable.default_selected_dot)
        unselectedDot = typedArray.getResourceId(R.styleable.ImageBanner_unselected_dot, R.drawable.default_unselected_dot)
        margin = typedArray.getInt(R.styleable.ImageBanner_margin, 0)

    }

    fun setImageList(imageList: List<ImageBannerModel>){
        viewPagerAdapter = ViewPagerAdapter(context, imageList, cornerRadius,margin, placeholder)
        viewPager!!.adapter = viewPagerAdapter
        imageCount = imageList.size
        setupDots(imageList.size)
        if(autoCycle){ autoSliding() }
    }

    fun setupDots(size: Int) {
        pagerDots!!.removeAllViews()
        dots = arrayOfNulls(size)

        for (i in 0 until size) {
            dots!![i] = ImageView(context)
            dots!![i]!!.setImageDrawable(ContextCompat.getDrawable(context, unselectedDot))
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            pagerDots!!.addView(dots!![i], params)
        }
        dots!![0]!!.setImageDrawable(ContextCompat.getDrawable(context, selectedDot))

        viewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                currentPage = position
                for (dot in dots!!) {
                    dot!!.setImageDrawable(ContextCompat.getDrawable(context,unselectedDot))
                }
                dots!![position]!!.setImageDrawable(ContextCompat.getDrawable(context, selectedDot))
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    fun autoSliding(){
        val handler = Handler()
        val Update = Runnable {
            if (currentPage == imageCount) {
                currentPage = 0
            }
            viewPager!!.setCurrentItem(currentPage++, true)
        }
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }
        }, delay, period)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        viewPagerAdapter?.setItemClickListener(itemClickListener)
    }
}



