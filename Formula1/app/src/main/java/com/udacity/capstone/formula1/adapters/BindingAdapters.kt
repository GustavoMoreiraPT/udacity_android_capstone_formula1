package com.udacity.capstone.formula1.adapters

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.udacity.capstone.formula1.R

@BindingAdapter("constructorSymbol")
fun bindDetailsConstructorSymbol(imageView: ImageView, name: String) {
    when(name){
        "Alfa Romeo" -> imageView.setImageResource(R.drawable.alfaromeo)
        "Ferrari" -> imageView.setImageResource(R.drawable.ferrari)
        "Haas F1 Team" -> imageView.setImageResource(R.drawable.haas)
        "McLaren" -> imageView.setImageResource(R.drawable.mclaren)
        "Racing Point" -> imageView.setImageResource(R.drawable.astonmartin)
        "Red Bull" -> imageView.setImageResource(R.drawable.redbull)
        "Renault" -> imageView.setImageResource(R.drawable.alpine)
        "Toro Rosso" -> imageView.setImageResource(R.drawable.alphatauri)
        "Mercedes" -> imageView.setImageResource(R.drawable.mercedes)
        "Williams" -> imageView.setImageResource(R.drawable.williams)
        else -> imageView.setImageResource(R.drawable.f1_main_logo)
    }
}

@BindingAdapter("driverImage")
fun bindDetailsDriverImage(imageView: ImageView, name: String){
    when(name){
        "Alonso" -> imageView.setImageResource(R.drawable.alonso)
        "Bottas" -> imageView.setImageResource(R.drawable.bottas)
        "Gasly" -> imageView.setImageResource(R.drawable.gasly)
        "Giovinazzi" -> imageView.setImageResource(R.drawable.giovinazzi)
        "Hamilton" -> imageView.setImageResource(R.drawable.hamilton)
        "Latifi" -> imageView.setImageResource(R.drawable.latifi)
        "Leclerc" -> imageView.setImageResource(R.drawable.leclerc)
        "Mazepin" -> imageView.setImageResource(R.drawable.mazepin)
        "Norris" -> imageView.setImageResource(R.drawable.norris)
        "Ocon" -> imageView.setImageResource(R.drawable.ocon)
        "Pérez" -> imageView.setImageResource(R.drawable.perez)
        "Räikkönen" -> imageView.setImageResource(R.drawable.raikonnen)
        "Ricciardo" -> imageView.setImageResource(R.drawable.ricciardo)
        "Russell" -> imageView.setImageResource(R.drawable.russell)
        "Sainz" -> imageView.setImageResource(R.drawable.sainz)
        "Schumacher" -> imageView.setImageResource(R.drawable.schumacher)
        "Stroll" -> imageView.setImageResource(R.drawable.stroll)
        "Tsunoda" -> imageView.setImageResource(R.drawable.tsunoda)
        "Verstappen" -> imageView.setImageResource(R.drawable.verstappen)
        "Vettel" -> imageView.setImageResource(R.drawable.vettel)
        else -> imageView.setImageResource(R.drawable.f1_main_logo)
    }
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?){
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Picasso.with(imgView.context)
            .load(imgUri)
            .into(imgView)
    }
}