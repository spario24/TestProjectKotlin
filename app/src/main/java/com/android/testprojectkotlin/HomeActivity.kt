package com.android.testprojectkotlin

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.testprojectkotlin.databinding.ActivityHomeBinding
import com.android.testprojectkotlin.fragments.TabFragment

class HomeActivity : AppCompatActivity() {

    lateinit var binding:ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loadImage()

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, TabFragment())
            .commit()
    }

    val image = "https://i.ibb.co/JBL07rG/rb-174706.png"
    val image2 = "https://i.ibb.co/Nnr7HQJ/rb-174706-24.png"

    fun loadImage(){
//        binding.avatar.addImage(image2)
    }

}