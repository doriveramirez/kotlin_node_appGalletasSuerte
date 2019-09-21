package com.example.galletassuerte


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cookieButton.setOnClickListener {
            requestPhrase()
        }
        buttonSearch.setOnClickListener{
            searchPhrase()
        }
        buttonReset.setOnClickListener{
            resetTimes()
        }
        buttonList.setOnClickListener(){
            showList()
        }
    }

    private fun showList() {
        val builder = AlertDialog.Builder(this@MainActivity)
        var phrases = ""
        var times = ""
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.103.81:40000/list"
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                builder.setMessage(response.toString())
                builder.setTitle("Lista de frases")
                builder.setNegativeButton("cerrar") { dialog, which -> }
                builder.show()
            },
            Response.ErrorListener { error ->
                // TODO: Handle error
            }
        )
        queue.add(jsonArrayRequest)
    }

    private fun resetTimes() {
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.103.81:40000/search?sid=-1"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                phraseSearch.text = "%s".format(response.get("frase").toString())
                searchId.text = "Veces: %s".format(response.get("veces").toString())
            },
            Response.ErrorListener { error ->
                // TODO: Handle error
            }
        )
        queue.add(jsonObjectRequest)
        searchId.setText("Veces que te ha salido la frase: 0")
    }

    /*override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }*/

    private fun requestPhrase(){
        // Instantiate the RequestQueue with the cache and network. Start the queue.
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.103.81:40000/today"
        cookieButton.setOnClickListener{}
        cookieButton.setVisibility(View.GONE)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                phrase.text = "%s".format(response.get("frase").toString())
            },
            Response.ErrorListener { error ->
                // TODO: Handle error
            }
        )
        queue.add(jsonObjectRequest)
    }

    private fun searchPhrase(){
        val queue = Volley.newRequestQueue(this)
        val textInto = textId.getText()
        val textIntChar: String = textId.getText().toString()
        val textInt = textIntChar.toIntOrNull()
        if ((!textId.getText().toString().equals(""))){
            if (textInt in 0..13){
                buttonReset.setVisibility(View.VISIBLE)
                val url = "http://192.168.103.81:40000/search?sid=$textInto"
                val jsonObjectRequest = JsonObjectRequest(
                    Request.Method.GET, url, null,
                    Response.Listener { response ->
                        phraseSearch.text = "%s".format(response.get("frase").toString())
                        searchId.text = "Veces que te ha salido la frase: %s".format(response.get("veces").toString())
                    },
                    Response.ErrorListener { error ->
                        // TODO: Handle error
                    }
                )
                queue.add(jsonObjectRequest)
            } else {
                phraseSearch.setText("El id introducido es incorrecto")
            }
        } else {
            phraseSearch.setText("Debe introducir un id")
        }
    }




    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }*/

}
