package com.example.consumircontentprovider

import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var notesRecycler: RecyclerView
    lateinit var noteRefreshButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteRefreshButton = findViewById(R.id.client_button_refresh)
        notesRecycler = findViewById(R.id.client_list)
        getContetProvider()

        noteRefreshButton.setOnClickListener {getContetProvider() }
    }

    //metodo responsavel por consulta a contet provider de outro aplicativo

    private fun getContetProvider(){
        try{
            val url = "content://com.couses.applicationcontentprovider.provider/notes"
            val data = Uri.parse(url)
            val cursor: Cursor? = contentResolver.query(data, null, null, null, "title")
            notesRecycler.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = ClientAdapter(cursor as Cursor)
            }
        }catch (ex:Exception){
            ex.printStackTrace()
        }
    }

}