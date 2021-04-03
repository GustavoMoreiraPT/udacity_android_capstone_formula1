package com.udacity.capstone.formula1.adapters

import android.widget.ImageView
import android.widget.TextView
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
        "Aston Martin" -> imageView.setImageResource(R.drawable.astonmartin)
        "Red Bull" -> imageView.setImageResource(R.drawable.redbull)
        "Renault" -> imageView.setImageResource(R.drawable.alpine)
        "AlphaTauri" -> imageView.setImageResource(R.drawable.alphatauri)
        "Mercedes" -> imageView.setImageResource(R.drawable.mercedes)
        "Williams" -> imageView.setImageResource(R.drawable.williams)
        else -> imageView.setImageResource(R.drawable.f1_main_logo)
    }
}

@BindingAdapter("driverImage")
fun bindDetailsDriverImage(imageView: ImageView, name: String){
    when(name){
        "alonso" -> imageView.setImageResource(R.drawable.alonso)
        "bottas" -> imageView.setImageResource(R.drawable.bottas)
        "gasly" -> imageView.setImageResource(R.drawable.gasly)
        "giovinazzi" -> imageView.setImageResource(R.drawable.giovinazzi)
        "hamilton" -> imageView.setImageResource(R.drawable.hamilton)
        "latifi" -> imageView.setImageResource(R.drawable.latifi)
        "leclerc" -> imageView.setImageResource(R.drawable.leclerc)
        "mazepin" -> imageView.setImageResource(R.drawable.mazepin)
        "norris" -> imageView.setImageResource(R.drawable.norris)
        "ocon" -> imageView.setImageResource(R.drawable.ocon)
        "perez" -> imageView.setImageResource(R.drawable.perez)
        "raikkonen" -> imageView.setImageResource(R.drawable.raikonnen)
        "ricciardo" -> imageView.setImageResource(R.drawable.ricciardo)
        "russell" -> imageView.setImageResource(R.drawable.russell)
        "sainz" -> imageView.setImageResource(R.drawable.sainz)
        "mick_schumacher" -> imageView.setImageResource(R.drawable.schumacher)
        "stroll" -> imageView.setImageResource(R.drawable.stroll)
        "tsunoda" -> imageView.setImageResource(R.drawable.tsunoda)
        "max_verstappen" -> imageView.setImageResource(R.drawable.verstappen)
        "vettel" -> imageView.setImageResource(R.drawable.vettel)
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

@BindingAdapter("permanentNumber")
fun bindTextViewToPermanentNumber(textView: TextView, number: Int) {
    val context = textView.context
    textView.text = number.toString()
}