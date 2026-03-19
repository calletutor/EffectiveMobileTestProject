package com.example.effectivemobiletestproject.feature.auth

import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.Spanned
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class LoginFragment : Fragment() {

    private var loginListener: OnLoginSuccessListener? = null

    override fun onAttach(context: android.content.Context) {
        super.onAttach(context)
        loginListener = context as? OnLoginSuccessListener
    }

    override fun onDetach() {
        super.onDetach()
        loginListener = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val emailEditText = view.findViewById<EditText>(R.id.etEmail)
        val passwordEditText = view.findViewById<EditText>(R.id.etPassword)
        val loginButton = view.findViewById<Button>(R.id.btnLogin)
        val leftButton = view.findViewById<Button>(R.id.btnLeft)
        val rightButton = view.findViewById<Button>(R.id.btnRight)

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
                return if (newText.isEmpty()) null
                else if (allowedRegex.matches(newText)) null
                else ""
            }
        }
        emailEditText.filters = arrayOf(emailFilter)

        val emailPattern = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
        fun updateLoginButtonState() {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString()
            loginButton.isEnabled = emailPattern.matches(email) && password.isNotEmpty()
        }
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
        updateLoginButtonState()

        loginButton.setOnClickListener {
            loginListener?.onLoginSuccess()
        }

        leftButton.setOnClickListener {
            startActivity(android.content.Intent(android.content.Intent.ACTION_VIEW, Uri.parse("https://vk.com/")))
        }
        rightButton.setOnClickListener {
            startActivity(android.content.Intent(android.content.Intent.ACTION_VIEW, Uri.parse("https://ok.ru/")))
        }
    }

    interface OnLoginSuccessListener {
        fun onLoginSuccess()
    }
}
