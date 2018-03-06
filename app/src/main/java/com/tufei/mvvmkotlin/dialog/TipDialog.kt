package com.tufei.mvvmkotlin.dialog

import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tufei.mvvmkotlin.R
import kotlinx.android.synthetic.main.dialog_fragment.*

/**
 * 1.确认按钮btn_positive默认文字为"确认"，取消按钮btn_negative默认文字为"取消"。
 *   可以通过调用相应的方法修改。
 * 2.你可设置确认或者取消按钮其一为View.GONE。布局会自动调整。
 * 3.如果你没有设置相应的点击事件，确认、取消按钮，都默认为View.GONE
 *
 * @author tufei
 * @date 2018/3/5.
 */
class TipDialog : DialogFragment() {
    val actions = mutableListOf<(() -> Unit)>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.Theme_Dialog)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.dialog_fragment, container, false)
    }

    override fun onResume() {
        super.onResume()
        actions.forEach {
            run {
                it()
            }
        }
    }

    /**
     是true
     */
    fun setCancel(isCancel: Boolean): TipDialog {
        isCancelable = isCancel
        return this
    }

    fun setTitle(title: CharSequence): TipDialog {
        val setTile = {
            tv_title.text = title
        }
        actions.add(setTile)
        return this
    }

    fun setTitle(@StringRes titleId: Int): TipDialog {
        val setTile = {
            tv_title.text = getText(titleId)
        }
        actions.add(setTile)
        return this
    }

    fun setContent(content: CharSequence): TipDialog {
        val setContent = {
            tv_content.text = content
        }
        actions.add(setContent)
        return this
    }

    fun setContent(@StringRes contentId: Int): TipDialog {
        val setContent = {
            tv_content.text = getText(contentId)
        }
        actions.add(setContent)
        return this
    }

    fun setPositiveText(positiveText: CharSequence): TipDialog {
        val setPositiveText = {
            tv_content.text = positiveText
        }
        actions.add(setPositiveText)
        return this
    }

    fun setPositiveText(@StringRes textId: Int): TipDialog {
        val setPositiveText = {
            btn_positive.text = getText(textId)
        }
        actions.add(setPositiveText)
        return this
    }

    fun setPositiveClick(action: (View) -> Unit): TipDialog {
        val positiveClick = {
            btn_positive.visibility = View.VISIBLE
            btn_positive.setOnClickListener {
                action(it)
            }
        }
        actions.add(positiveClick)
        return this
    }

    fun setNegativeText(negativeText: CharSequence): TipDialog {
        val setNegativeText = {
            btn_positive.visibility = View.VISIBLE
            btn_negative.text = negativeText
        }
        actions.add(setNegativeText)
        return this
    }

    fun setNegativeText(@StringRes textId: Int): TipDialog {
        val setNegativeText = {
            btn_negative.text = getText(textId)
        }
        actions.add(setNegativeText)
        return this
    }

    fun setNegativeClick(action: (View) -> Unit): TipDialog {
        val negativeClick = {
            btn_negative.setOnClickListener {
                action(it)
            }
        }
        actions.add(negativeClick)
        return this
    }

    fun toShow(fragmentManager: FragmentManager, tag: String): TipDialog {
        show(fragmentManager, tag)
        return this
    }
}