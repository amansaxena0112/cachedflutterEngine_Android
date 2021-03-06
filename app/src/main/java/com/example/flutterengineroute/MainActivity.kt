package com.example.flutterengineroute

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.flutterengineroute.FlutterEmbeddingActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        FlutterEmbeddingActivity.init(this)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        val anotherFlutterRouteButton = findViewById<FloatingActionButton>(R.id.anotherFlutterRouteButton)
        fab.setOnClickListener { view ->
            val flutterEmbeddingActivityIntent = FlutterEmbeddingActivity.createBuilder()
                            .initialRoute("counter")
                            .build(this)
                        startActivity(flutterEmbeddingActivityIntent)
        }
        anotherFlutterRouteButton.setOnClickListener {
              val flutterEmbeddingActivityIntent = FlutterEmbeddingActivity.createBuilder()
                    .initialRoute("anotherRoute")
                    .build(this)
                startActivity(flutterEmbeddingActivityIntent)
            }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }
}