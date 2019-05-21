package com.mtn.imagebanner

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mtn.imagebanner.imageslider.ImageBanner

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageList = ArrayList<ImageBannerModel>()

        imageList.add(ImageBannerModel("https://3.bp.blogspot.com/-uJtCbNrBzEc/XJUWQPOSrfI/AAAAAAAABUs/ZlReSwpfI3Ack60629Rv0N8hSrPFHb3TACLcBGAs/s1600/elephant.jpg"))
        imageList.add(ImageBannerModel("https://www.aussiespecialist.com/content/asp/en/sales-resources/image-and-video-galleries/_jcr_content/mainParsys/hero/image.adapt.1663.medium.jpg"))
        imageList.add(ImageBannerModel("https://www.theage.com.au/long-reads/our-patch/grampians/img/HERO_HR_Grampians.jpg"))


        val imageBanner = findViewById<ImageBanner>(R.id.image_banner)
        imageBanner.setImageList(imageList)

        imageBanner.setItemClickListener(object : ItemClickListener {
            override fun onItemSelected(position: Int) {
                // You can listen here.
            }
        })

    }
}
