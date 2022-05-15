package org.listadetarefas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    lateinit var campoText: EditText
    lateinit var btnadiciona: Button
    lateinit var listaTarefas : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        campoText = findViewById(R.id.editTextTarefas)
        btnadiciona = findViewById(R.id.buttonAdd)
        listaTarefas = findViewById(R.id.listaTarefas)

        // Model
        val list = ArrayList<String>()
        // Controller
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        // View
        listaTarefas.adapter = adapter

        btnadiciona.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                val text : String = campoText.text.toString()
                if ( text != " "){
                    list.add(text)
                    adapter.notifyDataSetChanged()
                    campoText.setText(" ")
                    campoText.requestFocus()
                }

                var textoDigitado = campoText.text.toString() + " Salvo"
                Toast.makeText(applicationContext, textoDigitado, Toast.LENGTH_LONG).show()
            }
        })

        listaTarefas.setOnItemClickListener{ parent, view, position, id ->
            campoText.setText(list.get(position))
        }

        listaTarefas.setOnItemLongClickListener { adapterView, view, i, l ->
            list.removeAt(i)
            adapter.notifyDataSetChanged()
            true
        }

    }
}