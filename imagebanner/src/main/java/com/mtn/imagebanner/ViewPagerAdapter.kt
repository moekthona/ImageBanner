package com.mtn.imagebanner

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ViewPagerAdapter(context: Context?, imageList: List<ImageBannerModel>, private var radius: Int,private var margin: Int, private var placeholder: Int) : PagerAdapter() {

    private var imageList: List<ImageBannerModel>? = imageList
    private var mContext : Context = context!!
    private var layoutInflater: LayoutInflater? = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?

    private var itemClickListener: ItemClickListener? = null

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun getCount(): Int {
        return imageList!!.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): View{

        val itemView = layoutInflater!!.inflate(R.layout.pager_row, container, false)

        val imageView = itemView.findViewById<ImageView>(R.id.image_view)
        val linearLayout = itemView.findViewById<LinearLayout>(R.id.linear_layout)
        val textView = itemView.findViewById<TextView>(R.id.text_view)

        if (imageList!![position].title != null){
            textView.text = imageList!![position].title
        }else{
            linearLayout.visibility = View.INVISIBLE
        }


        if(imageList!![position].imageUrl == null){
            Glide.with(mContext)
                .load(imageList!![position].imagePath!!) // Int
                .apply(RequestOptions().dontTransform().placeholder(placeholder))
                .apply(RequestOptions.bitmapTransform(RoundedCorners(mContext,radius,margin)))
                .into(imageView)
        }else{
            Glide.with(mContext)
                .load(imageList!![position].imageUrl!!) // String
                .apply(RequestOptions().dontTransform().placeholder(placeholder))
                .apply(RequestOptions.bitmapTransform(RoundedCorners(mContext,radius,margin)))
                .into(imageView)
        }

        container.addView(itemView)

        imageView.setOnClickListener{itemClickListener?.onItemSelected(position)}

        return itemView
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout)
    }

}