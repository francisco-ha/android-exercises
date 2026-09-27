package com.example.poo_1.superheroapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.core.view.isVisible
import com.example.poo_1.R
import com.example.poo_1.databinding.ActivityDetailSuperHeroBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

class DetailSuperHeroActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ID = "API_INFO"
    }

    private lateinit var binding:ActivityDetailSuperHeroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSuperHeroBinding.inflate(layoutInflater)

        //setContentView(R.layout.activity_detail_super_hero)
        setContentView(binding.root)

        val id = intent.getStringExtra(EXTRA_ID).orEmpty()
        getSuperHeroInformation(id)
    }

    fun getSuperHeroInformation(id: String) {

        CoroutineScope(Dispatchers.IO).launch {
            val superHeroDetail = getRetrofit().create(ApiService::class.java).getRickCharacterId(id)

            if (superHeroDetail.body() != null) {
                Log.i("API_INFO", superHeroDetail.toString())
                runOnUiThread {
                    val superHero = superHeroDetail.body()!!
                    createUi(superHero)
                    with(superHero.powerStats) {
                        Log.i("API_INFO", "Stats -> Combat: $combat, Durability: $durability, Speed: $speed, Strength: $strength, Intelligence: $intelligence, Power: $power")
                    }
                }
            }
        }

    }

    private fun createUi(superHero:RickAndMortyDetailCharacterDataResponse){
        Picasso.get().load(superHero.imageUrl).into(binding.ivSuperHeroDetail)
        binding.tvSuperHeroName.text = superHero.name
        prepareStats(superHero.powerStats)
        binding.tvSuperHeroStatus.text = superHero.status
        binding.tvSuperHeroType.text = superHero.type
    }
    private fun prepareStats(powerStats: PowerStatsResponse) {
        updateHeight(binding.viewCombat, powerStats.combat.toIntOrNull() ?: 0)
        updateHeight(binding.viewDurability, powerStats.durability.toIntOrNull() ?: 0)
        updateHeight(binding.viewSpeed, powerStats.speed.toIntOrNull() ?: 0)
        updateHeight(binding.viewStrength, powerStats.strength.toIntOrNull() ?: 0)
        updateHeight(binding.viewIntelligence, powerStats.intelligence.toIntOrNull() ?: 0)
        updateHeight(binding.viewPower, powerStats.power.toIntOrNull() ?: 0)
    }

    private fun updateHeight(view:View, stat:Int){
        val params = view.layoutParams
        params.height = pxToDp(stat.toFloat())
        view.layoutParams = params
    }
    private fun pxToDp(px:Float):Int{
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,px,resources.displayMetrics).roundToInt()
    }
    private fun getRetrofit(): Retrofit {
        val rickAndMortyRetrofit = Retrofit
            .Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return rickAndMortyRetrofit
    }
}