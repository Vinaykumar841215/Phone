package com.example.phone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home2)

        change(HomeFragment())
        var bottomNavigationView = findViewById<BottomNavigationView>(R.id.BottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener(){
            when(it.itemId){
                R.id.home ->{
                    change(HomeFragment())
                }
                R.id.notification ->{
                    change(NotificationFragment())
                }
                R.id.listf ->{
                    change(ListFragment())
                }
                R.id.profile ->{
                    change(ProfileFragment ())
                }
        }
            true
        }
    }
    private fun change(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.container,fragment).commit()
    }
    }
