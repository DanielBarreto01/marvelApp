package com.example.marvelapp.screens.inspectCharacter.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.data.api.models.comics.Comic
import com.example.marvelapp.data.api.models.series.Serie
import com.example.marvelapp.databinding.ComicViewBinding
import com.example.marvelapp.utils.loadImage

class RvInspectCharacter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var comics = emptyList<Comic>()
    var series = emptyList<Serie>()

    class ComicViewHolder(
        private val binding: ComicViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(comic: Comic) {
            with(binding) {
                tvComicName.text = comic.title
                tvComicDescription.text = if (comic.description?.length ?: 0 > 30) {
                    "${comic.description?.take(30)}..."
                } else {
                    comic.description
                }
                var imageUrl = "${comic.thumbnail.path}.${comic.thumbnail.extension}"
                if (imageUrl.startsWith("http:")) {
                    imageUrl = imageUrl.replace("http:", "https:")
                }
                ivComicPicture.loadImage(imageUrl)
                tvComicType.text = comic.variantDescription
            }
        }
    }

    class SerieViewHolder(
        private val binding: ComicViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(serie: Serie) {
            with(binding) {
                tvComicName.text = serie.title
                tvComicDescription.text = if (serie.description?.length ?: 0 > 30) {
                    "${serie.description?.take(30)}..."
                } else {
                    serie.description
                }
                var imageUrl = "${serie.thumbnail.path}.${serie.thumbnail.extension}"
                if (imageUrl.startsWith("http:")) {
                    imageUrl = imageUrl.replace("http:", "https:")
                }
                ivComicPicture.loadImage(imageUrl)
                tvComicType.text = serie.creators.items[0].name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) {
            val binding = ComicViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            ComicViewHolder(binding)
        } else {
            val binding = ComicViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            SerieViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ComicViewHolder) {
            holder.bind(comics[position])
        } else if (holder is SerieViewHolder) {
            holder.bind(series[position])
        }
    }

    override fun getItemCount(): Int = if (comics.isNotEmpty()) comics.size else series.size

    override fun getItemViewType(position: Int): Int {
        return if (comics.isNotEmpty()) 0 else 1
    }

    fun updateComics(newComics: List<Comic>) {
        comics = newComics
        series = emptyList()
        notifyDataSetChanged()
    }

    fun updateSeries(newSeries: List<Serie>) {
        series = newSeries
        comics = emptyList()
        notifyDataSetChanged()
    }
}