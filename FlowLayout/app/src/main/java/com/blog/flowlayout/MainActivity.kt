package com.blog.flowlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.TextView
import com.blog.flowlayout.databinding.ActivityMainBinding
import com.example.lib.FlowLayout

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var tabArray: Array<String>
    private var tabIndex: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tabArray = resources.getStringArray(R.array.tab_array)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add_item -> {
                tabIndex %= tabArray.size
                binding.flowlayout.addView(TextView(this).apply {
                    text = tabArray[tabIndex]
                    setBackgroundResource(R.drawable.shape_button_circular)
                    setPadding(12.dp2px(), 2.dp2px(), 12.dp2px(), 2.dp2px())
                    setTextSize(TypedValue.COMPLEX_UNIT_SP, 24f)
                })
                tabIndex++
                true
            }
            R.id.action_add_item_2 -> {
                tabIndex %= tabArray.size
                binding.flowlayout.addView(TextView(this).apply {
                    text = tabArray[tabIndex]
                    setBackgroundResource(R.drawable.shape_button_circular)
                    setPadding(12.dp2px(), 2.dp2px(), 12.dp2px(), 2.dp2px())
                    setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
                    layoutParams = ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
                })
                tabIndex++
                true
            }
            R.id.action_remove_item -> {
                true
            }
            R.id.action_line_vertical_gravity_top -> {
                binding.flowlayout.lineVerticalGravity = FlowLayout.LINE_VERTICAL_GRAVITY_TOP
                true
            }
            R.id.action_line_vertical_gravity_center_vertical -> {
                binding.flowlayout.lineVerticalGravity = FlowLayout.LINE_VERTICAL_GRAVITY_CENTER_VERTICAL
                true
            }
            R.id.action_line_vertical_gravity_bottom -> {
                binding.flowlayout.lineVerticalGravity = FlowLayout.LINE_VERTICAL_GRAVITY_BOTTOM
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}