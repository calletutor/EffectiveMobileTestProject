package com.example.effectivemobiletestproject

import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.Spanned
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity

class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailEditText = findViewById<EditText>(R.id.etEmail)
        val passwordEditText = findViewById<EditText>(R.id.etPassword)
        val loginButton = findViewById<Button>(R.id.btnLogin)

        // Запрещаем ввод кириллицы и любых символов вне стандартного email-набора
        val emailFilter = object : InputFilter {
            private val allowedRegex = Regex("[a-zA-Z0-9@._\\-]+")

            override fun filter(
                source: CharSequence,
                start: Int,
                end: Int,
                dest: Spanned,
                dstart: Int,
                dend: Int
            ): CharSequence? {
                val newText = source.subSequence(start, end)
                return if (newText.isEmpty()) {
                    null // удаление / backspace
                } else if (allowedRegex.matches(newText)) {
                    null // всё ок, пропускаем как есть
                } else {
                    "" // блокируем недопустимые символы (в т.ч. кириллицу)
                }
            }
        }

        emailEditText.filters = arrayOf(emailFilter)

        // Маска email и блокировка кнопки, если данные невалидны
        val emailPattern =
            Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$") // текст@текст.текст

        fun updateLoginButtonState() {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString()

            val isValidEmail = emailPattern.matches(email)
            val isPasswordFilled = password.isNotEmpty()

            loginButton.isEnabled = isValidEmail && isPasswordFilled
        }

        // изначально кнопка неактивна
        loginButton.isEnabled = false

        val watcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                updateLoginButtonState()
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        emailEditText.addTextChangedListener(watcher)
        passwordEditText.addTextChangedListener(watcher)

        loginButton.setOnClickListener {
            // Здесь уже гарантированно валидный email и непустой пароль
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString()

            // TODO: здесь будет логика входа
        }
    }
}

