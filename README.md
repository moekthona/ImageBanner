# ImageBanner
ImageBanner is library that canimplement image sliders in android apps.

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
