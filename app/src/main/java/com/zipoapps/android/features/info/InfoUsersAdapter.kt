package com.zipoapps.android.features.info

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zipoapps.android.R
import com.zipoapps.android.common.extensions.loadImage
import com.zipoapps.android.databinding.ItemUserBinding
import com.zipoapps.domain.models.User
import splitties.systemservices.layoutInflater

/**
 * @author andrii.zhumela
 * Created 27.12.2022
 */
class InfoUsersAdapter : RecyclerView.Adapter<InfoUsersAdapter.UserVH>() {

    var onUserClicked: ((User) -> Unit)? = null
    var items: MutableList<User>? = null
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserVH {
        return UserVH(
            ItemUserBinding.inflate(
                parent.context.layoutInflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserVH, position: Int) {
        holder.onBind(items?.get(position))
    }

    override fun getItemCount() = items?.size ?: 0

    override fun getItemId(position: Int): Long {
        return items?.get(position)?.userId?.toLong() ?: 0L
    }

    inner class UserVH(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: User?) {
            binding.itemUserNameTv.text = item?.name
            binding.itemUserPhotoIv.loadImage(item?.url)
            binding.itemUserPostsCountTv.text =
                binding.root.context.getString(R.string.posts_count_formatter, item?.postCount)
            binding.itemUserContainer.setOnClickListener { _ ->
                item?.let { onUserClicked?.invoke(it) }
            }
        }
    }
}