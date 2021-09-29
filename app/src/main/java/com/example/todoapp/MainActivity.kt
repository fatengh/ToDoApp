package com.example.todoapp

import android.annotation.SuppressLint
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var items: ArrayList<ToDoItem>
    private lateinit var rv: RecyclerView
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var rvAdapter: RecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        items = ArrayList()
        rv = findViewById(R.id.rvItems)
        rv.adapter = RecyclerViewAdapter(items)
        rv.layoutManager = LinearLayoutManager(this)
        fabAdd= findViewById(R.id.fabAdd)


        fabAdd.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(this)
            val layout = LinearLayout(this)
            layout.orientation = LinearLayout.VERTICAL
            val input = EditText(this)
            dialogBuilder.setMessage("Enter to-do item")
            input.setHint("Enter to do")
            input.inputType = InputType.TYPE_CLASS_TEXT
            dialogBuilder.setView(input)

            dialogBuilder.setPositiveButton("ADD", DialogInterface.OnClickListener {
                    dialog, id -> this.items.add(ToDoItem(input.text.toString()))
            })
                .setNegativeButton("CANCEL", DialogInterface.OnClickListener {
                        dialog, id -> dialog.cancel()
                })
            val alert = dialogBuilder.create()
            alert.setCancelable(true)
            alert.setTitle("TO DO")
            alert.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var itemsDeleted = 0
        for(i in items){
            if(i.checked){itemsDeleted++}
        }
        if(itemsDeleted > 0){
            Toast.makeText(this, "$itemsDeleted items deleted", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, "No items selected", Toast.LENGTH_LONG).show()
        }
        items.removeAll { item -> val checked = item.checked
            checked
        }
        rv.adapter?.notifyDataSetChanged()
        return super.onOptionsItemSelected(item)
    }





}


data class ToDoItem(val todo: String = "", var checked: Boolean = false)
