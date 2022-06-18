package iestr.gag.grafo6

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import iestr.gag.grafo6.databinding.FragmentDetallesBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class Detalles : Fragment() {

    lateinit var enlace: FragmentDetallesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        enlace= FragmentDetallesBinding.inflate(inflater, container,false)
        return enlace.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        enlace.botonInicidencia.setOnClickListener {
            if (enlace.tituloIncidencia.text.toString().isNullOrBlank()) {
                enlace.nombreError.visibility = View.VISIBLE

            }else{
                enlace.nombreError.visibility = View.GONE
            }

            if (enlace.descripcionIncidencia.text.toString().isNullOrBlank()) {
                enlace.descripcionError.visibility = View.VISIBLE

            }else{
                enlace.descripcionError.visibility = View.GONE
            }

            if(enlace.tipoIncidencia.text.toString().isNullOrBlank()){
                enlace.tipoError.visibility=View.VISIBLE

            }else{
                enlace.tipoError.visibility=View.GONE
            }

            if(enlace.ubicacionIncidencia.text.toString().isNullOrBlank()){
                enlace.calleError.visibility=View.VISIBLE

            } else {
                enlace.calleError.visibility=View.GONE
            }

            if (enlace.nombreError.visibility==View.GONE &&
                enlace.descripcionError.visibility==View.GONE &&
                enlace.tipoError.visibility==View.GONE &&
                enlace.calleError.visibility==View.GONE){

                Toast.makeText(context, "Informe Creado", Toast.LENGTH_SHORT).show()
                insertar()
                findNavController().navigate(R.id.action_detalles_to_portadaFragment)
        }

        enlace.imageButton.setOnClickListener{
                findNavController().navigate(R.id.action_detalles_to_maps)
            }
        }
    }

    private fun insertar() {
        val sdf = SimpleDateFormat("yyyy/M/dd")
        val fecha = sdf.format(Date())


        val nombre_incidencia: String = enlace.tituloIncidencia.text.toString()

        val descripcion: String = enlace.descripcionIncidencia.text.toString()

        val tipo_incidencia: String = enlace.tipoIncidencia.text.toString()

        val ubicacion: String = enlace.ubicacionIncidencia.text.toString()

        val imagen :String=enlace.imagenIncidencia.text.toString()

        var dni_usuario=""
        arguments?.let {
            val datos = DetallesArgs.fromBundle(it)
            dni_usuario = datos.dniUsuario
        }



        // Instantiate the RequestQueue.
        val url = "http://192.168.43.44/insertarIncidencia.php"
        val jsonRequest = object : StringRequest(
            Request.Method.POST, url, { response ->

            },
            Response.ErrorListener { error: VolleyError? ->
                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()

            }) {
            override fun getParams(): MutableMap<String, String> {
                val parametros = HashMap<String, String>()
                parametros.put("nombre_incidencia", nombre_incidencia)
                parametros.put("descripcion", descripcion)
                parametros.put("tipo_incidencia", tipo_incidencia)
                parametros.put("ubicacion", ubicacion)
                parametros.put("fecha",fecha)
                parametros.put("dni_usuario",dni_usuario)
                parametros.put("imagen",imagen)
                return parametros
            }


        }
        Volley.newRequestQueue(context).add(jsonRequest)
    }
}