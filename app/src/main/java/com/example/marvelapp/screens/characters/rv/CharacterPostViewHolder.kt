package com.example.rickandmortyapp.ui.screens.characters.rv

// Ojo con los nombres de los paquetes, que no sea solo copiar y pegar clases

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.databinding.CharacterViewBinding
import com.example.marvelapp.R
import com.example.marvelapp.data.api.models.Character
import com.example.marvelapp.utils.limitLength
import com.example.marvelapp.utils.loadImage

class CharacterPostViewHolder(
    private val binding: CharacterViewBinding,


): RecyclerView.ViewHolder(binding.root) {
    fun bind(characters: Character) {
        with(binding) {


            tvCharacterName.text = characters.name

            tvDescription.text = tvDescription.context.getString(
                R.string.character_gender,
                characters.description.limitLength(10)
            )

//            El error es
//            Caused by: java.io.IOException: Cleartext HTTP traffic to i.annihil.us not permitted
//                    at com.android.okhttp.HttpHandler$CleartextURLFilter.checkURLPermitted(HttpHandler.java:127)
//            at com.android.okhttp.internal.huc.HttpURLConnectionImpl.execute(HttpURLConnectionImpl.java:462)
//            at com.android.okhttp.internal.huc.HttpURLConnectionImpl.connect(HttpURLConnectionImpl.java:131)
//            at com.bumptech.glide.load.data.HttpUrlFetcher.loadDataWithRedirects(HttpUrlFetcher.java:93)
//            Importante apoyarse en los logs de la aplicación
//            Una solución es habilitar el trafico con http https://stackoverflow.com/a/59808129
//            Otra solución (recomendada) es modificar el imageUrl para que reemplace "http" por "https"

            val imageUrl = "${characters.thumbnail.path}.${characters.thumbnail.extension}"
            Log.d("CharacterPostViewHolder", "Final Image URL: $imageUrl")
            ivCharacterPicture.loadImage(imageUrl)
        }
    }
}