package com.ali74.libkot.patternBuilder

import android.app.Activity
import android.graphics.Typeface
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.ali74.libkot.R
import com.ali74.libkot.utils.AppTheme
import com.google.android.material.snackbar.Snackbar

class SnackBarBuilder {

    private var snackBar: Snackbar? = null

    private var message = ""
    private var actionText = ""
    private var action: View.OnClickListener? = null
    private var duration = Snackbar.LENGTH_SHORT
    private var animation = Snackbar.ANIMATION_MODE_FADE
    private var snackFont = AppTheme.SnackBar.fontSnackBar

    private var messageTextColor = AppTheme.SnackBar.snackMessageTextColor
    private var actionTextColor = AppTheme.SnackBar.snackActionTextColor
    private var actionBackgroundColor = AppTheme.SnackBar.snackActionBackgroundColor

    fun setMessage(
        message: String,
        @ColorRes textColor: Int = AppTheme.SnackBar.snackMessageTextColor
    ) = apply {
        this.message = message
        messageTextColor = textColor
    }

    fun setActionText(
        text: String = "",
        @ColorRes textColor: Int = AppTheme.SnackBar.snackActionTextColor,
        @ColorRes backgroundColor: Int = AppTheme.SnackBar.snackActionBackgroundColor,
    ) = apply {
        actionText = text
        actionTextColor = textColor
        actionBackgroundColor = backgroundColor
    }

    fun setAction(action: View.OnClickListener? = null) = apply {
        this.action = action
    }

    fun setDuration(duration: Int) = apply {
        this.duration = duration
    }

    fun setFont(font: String) = apply {
        this.snackFont = font
    }

    fun setAnimation(animation: Int) = apply {
        this.animation = animation
    }

    fun show(activity: Activity) {
        snackBar = if (action != null)
            Snackbar.make(
                activity.findViewById(android.R.id.content), message, duration
            ).setAction(actionText, action)
                .setAnimationMode(animation)
                .setBackgroundTint(
                    ContextCompat.getColor(
                        activity,
                        AppTheme.SnackBar.snackBackgroundColor
                    )
                )
        else Snackbar.make(
            activity.findViewById(android.R.id.content), message, duration
        ).setAnimationMode(animation)
            .setBackgroundTint(
                ContextCompat.getColor(
                    activity,
                    AppTheme.SnackBar.snackBackgroundColor
                )
            )

        snackBar?.apply {
            val text: TextView = view.findViewById(R.id.snackbar_text)
            val action: TextView = view.findViewById(R.id.snackbar_action)
            if (snackFont != "") {
                text.typeface = Typeface.createFromAsset(activity.assets, snackFont)
                action.setTypeface(
                    Typeface.createFromAsset(activity.assets, snackFont),
                    Typeface.BOLD
                )
            }
            action.setTextColor(ContextCompat.getColor(activity, actionTextColor))
            action.setBackgroundColor(ContextCompat.getColor(activity, actionBackgroundColor))

            text.setTextColor(ContextCompat.getColor(activity, messageTextColor))

        }

        snackBar?.apply {
            if (!isShown)
                show()
        }
    }

    fun show(v: View) {
        snackBar = if (action != null)
            Snackbar.make(
                v, message, duration
            ).setAction(actionText, action)
                .setAnimationMode(animation)
                .setBackgroundTint(
                    ContextCompat.getColor(
                        v.context,
                        AppTheme.SnackBar.snackBackgroundColor
                    )
                )
        else Snackbar.make(
            v, message, duration
        ).setAnimationMode(animation)
            .setBackgroundTint(
                ContextCompat.getColor(
                    v.context,
                    AppTheme.SnackBar.snackBackgroundColor
                )
            )

        snackBar?.apply {
            val text: TextView = view.findViewById(R.id.snackbar_text)
            val action: TextView = view.findViewById(R.id.snackbar_action)
            if (snackFont != "") {
                text.typeface = Typeface.createFromAsset(view.context.assets, snackFont)
                action.setTypeface(
                    Typeface.createFromAsset(view.context.assets, snackFont),
                    Typeface.BOLD
                )
            }
            action.setTextColor(ContextCompat.getColor(view.context, actionTextColor))
            action.setBackgroundColor(ContextCompat.getColor(view.context, actionBackgroundColor))

            text.setTextColor(ContextCompat.getColor(view.context, messageTextColor))

        }

        snackBar?.apply {
            if (!isShown)
                show()
        }

    }
}