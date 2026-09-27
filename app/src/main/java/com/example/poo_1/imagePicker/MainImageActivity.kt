package com.example.poo_1.imagePicker

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.appcompat.app.AppCompatActivity
import com.example.poo_1.R
import com.example.poo_1.databinding.ActivityMainImageBinding

class MainImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainImageBinding;

    /*private val responseLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ activityResult ->

        if(activityResult.resultCode == 77){
            return exito
        }

    }
    */
    val pickMedia = registerForActivityResult(PickVisualMedia()){ uri ->
        if (uri!=null){
            binding.ivImage.setImageURI(uri)
        }else{

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainImageBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.btnImage.setOnClickListener {
            val gif = "image/gif"
            //pickMedia.launch(PickVisualMediaRequest(PickVisualMedia.SingleMimeType(gif)))
            pickMedia.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))

            //val intent = Intent(this,NombreClase::class.java)
            //responseLauncher.launch(intent)

        }

    }
}






