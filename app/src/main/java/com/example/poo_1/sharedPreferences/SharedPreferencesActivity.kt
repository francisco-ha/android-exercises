package com.example.poo_1.sharedPreferences

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.poo_1.databinding.ActivitySharedPreferencesBinding
import com.example.poo_1.App.Companion.prefs

class SharedPreferencesActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySharedPreferencesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySharedPreferencesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()
        checkUserValues()
    }

    fun checkUserValues(){
        if (prefs.getName().isNotEmpty()){
            goToDetail()
        }
    }

    private fun initUi() {
        binding.btnContinue.setOnClickListener{
            accessToDetail()
        }
    }

    private fun accessToDetail() {
        if (binding.etName.text.toString().isNotEmpty()){
            prefs.saveName(binding.etName.text.toString())
            prefs.saveVIP(binding.cbVip.isChecked)
            goToDetail()
        }
        else{

        }
    }
    private fun goToDetail(){
        startActivity(Intent(this,ResultActivity::class.java))
    }
}