package com.prestosa.activity4

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.prestosa.activity4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize SharedPreferences
        sharedPreferences = getPreferences(Context.MODE_PRIVATE)

        // Load saved state when activity starts
        loadStateFromPreferences()

        binding.saveB.setOnClickListener {
            saveStateToPreferences()
            // Add any other actions you want to perform on button click
            Toast.makeText(this, "Settings saved!", Toast.LENGTH_LONG).show()
        }
    }

    private fun saveStateToPreferences() {
        // Save the state of UI elements to SharedPreferences
        val editor = sharedPreferences.edit()
        editor.putString("email", binding.emailET.text.toString())
        editor.putString("nickname", binding.nicknameET.text.toString())
        editor.putBoolean("allowNotifications", binding.allowTV.isChecked)
        editor.putInt("selectedTheme", binding.themesRG.checkedRadioButtonId)
        editor.apply()
    }

    private fun loadStateFromPreferences() {
        // Load the state of UI elements from SharedPreferences
        binding.emailET.setText(sharedPreferences.getString("email", ""))
        binding.nicknameET.setText(sharedPreferences.getString("nickname", ""))
        binding.allowTV.isChecked = sharedPreferences.getBoolean("allowNotifications", false)

        val selectedThemeId = sharedPreferences.getInt("selectedTheme", -1)
        if (selectedThemeId != -1) {
            binding.themesRG.check(selectedThemeId)
        }
    }
}