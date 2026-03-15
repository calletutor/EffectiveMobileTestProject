package com.example.effectivemobiletestproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.effectivemobiletestproject.databinding.ActivityHostBinding
import com.example.effectivemobiletestproject.feature.auth.LoginFragment
import com.example.effectivemobiletestproject.feature.home.HomeFragment

class MainActivity : AppCompatActivity(),
    LoginFragment.OnLoginSuccessListener,
    HomeFragment.OnNavigateToCourseListener {

    private lateinit var binding: ActivityHostBinding
    private lateinit var navController: androidx.navigation.NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomBar.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomBar.visibility = when (destination.id) {
                R.id.loginFragment -> android.view.View.GONE
                else -> android.view.View.VISIBLE
            }
        }
    }

    override fun onLoginSuccess() {
        navController.navigate(R.id.homeFragment)
    }

    override fun onCourseSelected(
        title: String,
        description: String,
        price: String,
        rate: String,
        startDate: String
    ) {
        val args = Bundle().apply {
            putString("title", title)
            putString("description", description)
            putString("price", price)
            putString("rate", rate)
            putString("startDate", startDate)
        }
        navController.navigate(R.id.courseFragment, args)
    }
}
