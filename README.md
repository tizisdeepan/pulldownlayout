# PullDownLayout
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?style=flat-square)](https://www.apache.org/licenses/LICENSE-2.0.html)
[![](https://jitpack.io/v/tizisdeepan/pulldownlayout.svg)](https://jitpack.io/#tizisdeepan/pulldownlayout)

![Screenshot 2](https://github.com/tizisdeepan/pulldownlayout/raw/master/Screenshots/ss.gif)
## What is Pull Down Layout?
PullDownLayout is a small library that allows you to implement a view that can be dragged down your layout. PullDownLayout can also be used to implement Pull-To-Dismiss feature for your activities and fragments.

## Implementation
### [1] In your app module gradle file

``` groovy
dependencies {
    implementation 'com.github.tizisdeepan:pulldownlayout:1.0.0'
}
```

### [2] In your project level gradle file

``` groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

### [3] Use PullBackLayout in your layout.xml

``` xml
<com.layout.pulldown.PullDownLayout
        android:id="@+id/pullDown"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">
  <!--Your Views-->
</com.layout.pulldown.PullDownLayout>
```

### [4] Set Callback for PullBackLayout in your Activity or Fragment

``` kotlin
class FragmentClass: Fragment(), PullDownLayout.Callback {
    override fun onPullStart() {
    }

    override fun onPull(progress: Float) {
    }

    override fun onPullCancel() {
    }

    override fun onPullComplete() {
        (ctx as MainActivity).onBackPressed()
    }
    ...
}
```
[Note] Check out sample project for cool pull down transitions.
