package com.digitalcreative.coronaapps.utils

import android.app.ActionBar
import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.view.animation.Transformation
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.math.roundToInt

object AnimUtil {
    const val TYPE_EXPAND = 1
    const val TYPE_COLLAPSE = 2

    fun isExpanded(view: View): Boolean = view.visibility == View.VISIBLE

    fun animateExpandTextLine(view: View) {
        val matchParentMeasureSpec = View.MeasureSpec.makeMeasureSpec(
            (view.parent as View).width,
            View.MeasureSpec.EXACTLY
        )
        val wrapContentMeasureSpec = View.MeasureSpec.makeMeasureSpec(
            0,
            View.MeasureSpec.UNSPECIFIED
        )
        view.measure(matchParentMeasureSpec, wrapContentMeasureSpec)

        val targetHeight = view.measuredHeight

        view.layoutParams.height = 1
//        view.visible()
//        view.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
        val a: Animation = object : Animation() {
            override fun applyTransformation(
                interpolatedTime: Float,
                t: Transformation?
            ) {
                view.layoutParams.height =
                    if (interpolatedTime == 1f)
                        ConstraintLayout.LayoutParams.WRAP_CONTENT
                    else
                        (targetHeight * interpolatedTime).toInt()
                view.requestLayout()
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        a.duration = (targetHeight / view.context.resources.displayMetrics.density).toLong()
        view.startAnimation(a)
        a.setAnimationListener(object: Animation.AnimationListener{
            override fun onAnimationRepeat(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                (view as TextView).maxLines = Integer.MAX_VALUE
            }

            override fun onAnimationStart(p0: Animation?) {

            }

        })
    }

    fun animateCollapseTextLine(view: View, resources: Resources) {
        val initialHeight = view.measuredHeight
        val a: Animation = object : Animation() {
            override fun applyTransformation(
                interpolatedTime: Float,
                t: Transformation
            ) {
                if (interpolatedTime == 1f) {
//                    view.gone()
                    (view as TextView).maxLines = 2
                } else {
                    view.layoutParams.height =
                        view.layoutParams.height - (view.layoutParams.height * interpolatedTime).toInt()
                    view.requestLayout()
                }
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        a.duration = (initialHeight / view.context.resources.displayMetrics.density).toLong()
        view.startAnimation(a)
    }

    fun animateContentExpand(view: View) {
        val matchParentMeasureSpec = View.MeasureSpec.makeMeasureSpec(
            (view.parent as View).width,
            View.MeasureSpec.EXACTLY
        )
        val wrapContentMeasureSpec = View.MeasureSpec.makeMeasureSpec(
            0,
            View.MeasureSpec.UNSPECIFIED
        )
        view.measure(matchParentMeasureSpec, wrapContentMeasureSpec)

        val targetHeight = view.measuredHeight

        view.layoutParams.height = 1
        view.visible()

        val a: Animation = object : Animation() {
            override fun applyTransformation(
                interpolatedTime: Float,
                t: Transformation?
            ) {
                view.layoutParams.height =
                    if (interpolatedTime == 1f)
                        ConstraintLayout.LayoutParams.WRAP_CONTENT
                    else
                        (targetHeight * interpolatedTime).toInt()
                view.requestLayout()
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        a.duration = (targetHeight / view.context.resources.displayMetrics.density).toLong()
        view.startAnimation(a)
    }

    fun animateContentCollapse(view: View) {
        val initialHeight = view.measuredHeight
        val a: Animation = object : Animation() {
            override fun applyTransformation(
                interpolatedTime: Float,
                t: Transformation
            ) {
                if (interpolatedTime == 1f) {
                    view.gone()
                } else {
                    view.layoutParams.height =
                        initialHeight - (initialHeight * interpolatedTime).toInt()
                    view.requestLayout()
                }
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        a.duration = (initialHeight / view.context.resources.displayMetrics.density).toLong()
        view.startAnimation(a)
    }

    fun animateRotate(view: View, type: Int) {
        val anim = when (type) {
            TYPE_EXPAND -> RotateAnimation(
                0F,
                90F,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
            )
            TYPE_COLLAPSE -> RotateAnimation(
                90F,
                0F,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
            )
            else -> throw IllegalArgumentException("Type not found!")
        }.apply {
            duration = 150
            fillAfter = true
        }

        view.animation = anim
    }
}