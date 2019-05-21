# ImageBanner
ImageBanner is library that can implement image sliders in android apps.

## Installation

### Step 1

```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
 ```

### Step 2
```
dependencies {
  	implementation 'com.github.moekthona:ImageBanner:0.0.2'
}
 ```
# Usage
#### Add permission manifest 
```xml
	<uses-permission android:name="android.permission.INTERNET"></uses-permission>
```
#### Sample XML code 
```xml
        <com.mtn.imagebanner.imageslider.ImageBanner
            android:id="@+id/image_banner"
            android:layout_width="match_parent"
            android:layout_height="200dp"
            app:auto_cycle="true"
            app:period="1000"
            app:delay="0">
       </com.mtn.imagebanner.imageslider.ImageBanner>

```

#### Custom Indicator
```xml
 	app:selected_dot="@drawable/default_selected_dot"
        app:unselected_dot="@drawable/default_unselected_dot"
	
```

#### Custom Placeholder
```xml
	app:placeholder="@drawable/placeholder"
```
#### Set Corner Radius
```xml
	app:corner_radius="40"
        app:margin="20"
```
###  Kotlin
```
	val imageList = ArrayList<ImageBannerModel>()

        imageList.add(ImageBannerModel("https://www.aussiespecialist.com/content/asp/en/sales-resources/image-and-video-galleries/_jcr_content/mainParsys/hero/image.adapt.1663.medium.jpg"))
        imageList.add(ImageBannerModel("https://www.theage.com.au/long-reads/our-patch/grampians/img/HERO_HR_Grampians.jpg"))
        imageList.add(ImageBannerModel("https://d2jh4nh0bx3cra.cloudfront.net/PRODUCT_IMAGE/5820/Grampians2.jpg"))


        val imageBanner = findViewById<ImageBanner>(R.id.image_banner)
        imageBanner.setImageList(imageList)

        imageBanner.setItemClickListener(object : ItemClickListener {
            override fun onItemSelected(position: Int) {
                // You can listen here.
            }
        })
```
