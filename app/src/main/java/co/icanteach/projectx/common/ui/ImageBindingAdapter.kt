package co.icanteach.projectx.common.ui

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageBindingAdapter {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setUrl(imageView: ImageView, imageUrl: String?) {
        Glide.with(imageView.context)
            .load("https://image.tmdb.org/t/p/w500/$imageUrl")
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("visibleIf")
    fun changeVisibility(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }
}