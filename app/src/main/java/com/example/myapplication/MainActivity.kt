package com.example.myapplication

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.example.myapplication.databinding.ActivityMainBinding
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.Random

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private lateinit var playerScoreTextView: TextView
    private lateinit var computerScoreTextView: TextView
    private lateinit var rockButton: Button
    private lateinit var paperButton: Button
    private lateinit var scissorsButton: Button
    private var playerScore = 0
    private var computerScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Находим элементы по ID
        resultTextView = findViewById(R.id.resultTextView)
        playerScoreTextView = findViewById(R.id.playerScoreTextView)
        computerScoreTextView = findViewById(R.id.computerScoreTextView)
        rockButton = findViewById(R.id.rockButton)
        paperButton = findViewById(R.id.paperButton)
        scissorsButton = findViewById(R.id.scissorsButton)

        // Обработка нажатий на кнопки
        rockButton.setOnClickListener { playGame("камень") }
        paperButton.setOnClickListener { playGame("бумага") }
        scissorsButton.setOnClickListener { playGame("ножницы") }
    }

    // Логика игры
    private fun playGame(playerChoice: String) {
        // Генерация выбора компьютера
        val choices = arrayOf("камень", "бумага", "ножницы")
        val computerChoice = choices[Random().nextInt(choices.size)]

        // Определение результата
        val result = when {
            playerChoice == computerChoice -> "Ничья!"
            (playerChoice == "камень" && computerChoice == "ножницы") ||
                    (playerChoice == "бумага" && computerChoice == "камень") ||
                    (playerChoice == "ножницы" && computerChoice == "бумага") -> {
                playerScore++ // Увеличиваем счет игрока
                "Вы выиграли!"
            }
            else -> {
                computerScore++ // Увеличиваем счет компьютера
                "Вы проиграли!"
            }
        }

        // Обновляем счетчики
        playerScoreTextView.text = "Игрок: $playerScore"
        computerScoreTextView.text = "Компьютер: $computerScore"

        // Вывод результата
        resultTextView.text = "Вы выбрали: $playerChoice\nКомпьютер выбрал: $computerChoice\n$result"
    }
}
