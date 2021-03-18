package com.example.testkotlincarlos.interactors
import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load

fun Context.toast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun ImageView.loadUrl(url: String){
    this.load(url)
}

fun RecyclerView.configuration(layoutManager: RecyclerView.LayoutManager, adapter: RecyclerView.Adapter<*>){
    this.layoutManager = layoutManager
    this.setHasFixedSize(true)
    this.adapter = adapter
}

fun TextView.setTextBundle(bundle: Bundle, key: String){
    this.text = bundle.getString(key)
}