package com.example.widgets

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.widgets.databinding.ActivityMainBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var kontrol=false
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
            kontrol=isChecked
            if (kontrol)
            {
                val secilenButon=findViewById<Button>(binding.toggleButton.checkedButtonId)
                val butonYazi=secilenButon.text.toString()
                Log.e("Seçilen",butonYazi)
            }

        }

        val ulkeler=ArrayList<String>()
        ulkeler.add("Türkiye")
        ulkeler.add("İngiltere")
        ulkeler.add("Hollanda")
        ulkeler.add("Norveç")

        val arrayAdapter =ArrayAdapter(this,android.R.layout.simple_list_item_activated_1,ulkeler)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)


        binding.buttonBasla.setOnClickListener {
            binding.progressBar.visibility= View.VISIBLE
        }

        binding.buttonDur.setOnClickListener {
            binding.progressBar.visibility= View.INVISIBLE
        }

        binding.textViewSlider.text=binding.slider.progress.toString() // text view'e slider'ın ilk değerini atmaak

        binding.slider.setOnSeekBarChangeListener(object :OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.textViewSlider.text=progress.toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })




        binding.buttonSaat.setOnClickListener {
            val tp=MaterialTimePicker.Builder()
                .setTitleText("Saat Seçiniz")
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()

            tp.show(supportFragmentManager,"Saat")
            tp.addOnPositiveButtonClickListener {
                binding.editTextSaat.setText("${tp.hour}:${tp.minute}")
            }
        }


        binding.buttonTarih.setOnClickListener {
            val dt=MaterialDatePicker.Builder.datePicker()
                .setTitleText("Tarih Seçiniz")
                .build()

            dt.show(supportFragmentManager,"Tarih")
            dt.addOnPositiveButtonClickListener {
                val df=SimpleDateFormat("EEEE, MMM, d, yyyy", Locale.getDefault())
                val tarih=df.format(it)
                binding.editTextTarih.setText(tarih)
            }
        }






        binding.buttonGoster.setOnClickListener {
            Log.e("Widgets","Switch Durum : ${binding.switch1.isChecked }")
            if (kontrol){
            val secilenButon=findViewById<Button>(binding.toggleButton.checkedButtonId)
            val butonYazi=secilenButon.text.toString()
            Log.e("Goster Uzerinden Sonuc","Toggle Durum: $butonYazi")
            }
            val ulke=binding.autoCompleteTextView.text.toString()
            Log.e("Sonuc Ulke:",ulke)

            Log.e("Sonuc:","Slider Deger:${binding.slider.progress}")
        }





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}