package com.layout.pulldown

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_image_preview.*

class ImagePreviewFragment: Fragment(), PullDownLayout.Callback {
    override fun onPullStart() {
    }

    override fun onPull(progress: Float) {
        root.alpha = 1.0f - progress * 5.0f
        preview.scaleX = 1.0f - progress
        preview.scaleY = 1.0f - progress
    }

    override fun onPullCancel() {
    }

    override fun onPullComplete() {
        (ctx as MainActivity).onBackPressed()
    }

    lateinit var ctx: Context
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        ctx = inflater.context
        return inflater.inflate(R.layout.fragment_image_preview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pullDown.setCallback(this)
    }
}