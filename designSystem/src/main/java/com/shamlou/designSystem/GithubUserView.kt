package com.shamlou.designSystem

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.shamlou.designSystem.databinding.GithubUserViewBinding

/**
 * represents UI of a Search result, since we made a
 * custom view, we can share it between other
 * feature modules, design system module can
 * be a different project to be used between projects
 */
class GithubUserView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    FrameLayout(context, attrs, defStyleAttr) {

    private var mBinding: GithubUserViewBinding? = null

    init {

        initView()
        addView(mBinding?.root)
    }

    private fun initView() {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mBinding = GithubUserViewBinding.inflate(inflater)
    }

    fun setSearchTitle(searchTitle: String?) {

        mBinding?.textViewSearchTitle?.text = searchTitle
    }

    fun setStartAndEnd(startAndEnd: String?) {

        mBinding?.textViewSearchStartAndEndAt?.text = startAndEnd
    }

    fun setEarningsAmount(earningsAmount: String?) {

        mBinding?.textViewSearchEarningsAmount?.text = earningsAmount
    }

    fun setExtraBriefing(extraBriefing: String?) {

        mBinding?.textViewSearchExtraBriefing?.text = extraBriefing
    }

    /**
     * i couldn't load Search image since it requires authentication
     */
    fun setImageUrl(imageUrl: String) {

    }

}
