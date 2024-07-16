package com.example.widgets

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.widgets.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.buttonOku.setOnClickListener {
            val alinanVeri=binding.editTextGirdi.text.toString()
            binding.textViewSonuc.text=alinanVeri
        }


        binding.buttonResim1.setOnClickListener {
            binding.imageView.setImageResource(R.drawable.baseline_mood_24)
        }

        binding.buttonResim2.setOnClickListener {
            binding.imageView.setImageResource(resources.getIdentifier("baseline_mood_bad_24","drawable",packageName))
        }

        binding.switch1.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked ){
                Log.e("Widgets","Switch : ON")
            }
            else{
                Log.e("Widgets","Switch : OFF")
            }

        }
        binding.toggleButton.addOnButtonCheckedListener { group, checkedId, isChecked ->
            val secilenButon=findViewById<Button>(binding.toggleButton.checkedButtonId)
            val butonYazi=secilenButon.text.toString()
            Log.e("Seçilen",butonYazi)
        }
        binding.buttonGoster.setOnClickListener {
            Log.e("Widgets","Switch Durum : ${binding.switch1.isChecked }")
            val secilenButon=findViewById<Button>(binding.toggleButton.checkedButtonId)
            val butonYazi=secilenButon.text.toString()
            Log.e("Goster Uzerinden Sonuc","Toggle Durum: $butonYazi")
        }




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}