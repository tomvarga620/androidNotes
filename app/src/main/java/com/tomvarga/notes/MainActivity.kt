package com.tomvarga.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.tomvarga.notes.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import net.danlew.android.joda.JodaTimeAndroid

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // init yodatime library
        JodaTimeAndroid.init(this)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        setSupportActionBar(binding.myToolbar)

        navController = Navigation.findNavController(this,R.id.my_nav_host_fragment)

        NavigationUI.setupWithNavController(bottomNav,navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,null)
    }

}
