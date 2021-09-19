package io.afdon.list.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.afdon.list.databinding.ItemTvShowBinding
import io.afdon.list.model.TvShow

class TvShowsAdapter : RecyclerView.Adapter <TvShowsAdapter.Holder>() {

    private val listDiffer = AsyncListDiffer(this, object: DiffUtil.ItemCallback<TvShow>() {

        override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
            return oldItem == newItem
        }
    })

    fun submitList(items: List<TvShow>) {
        listDiffer.submitList(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
        Holder(ItemTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = listDiffer.currentList.size

    override fun onBindViewHolder(holder: Holder, position: Int) =
        holder.bind(listDiffer.currentList[position])

    class Holder(
        private val binding: ItemTvShowBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(tvShow: TvShow) {
            binding.tvShow = tvShow
        }
    }
}