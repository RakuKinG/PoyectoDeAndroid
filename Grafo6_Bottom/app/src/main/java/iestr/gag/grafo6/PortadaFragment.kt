package iestr.gag.grafo6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import iestr.gag.grafo6.databinding.FragmentPortadaBinding
import java.util.regex.Pattern


class PortadaFragment : Fragment() {

    lateinit var enlace: FragmentPortadaBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        enlace= FragmentPortadaBinding.inflate(inflater, container,false)
        return enlace.root
    }


    fun validarDNI():Boolean{
        return validarDNIPattern(enlace.dni.text.toString().trim())
    }
    fun validarDNIPattern(dni:String):Boolean{
        return Pattern.compile("[0-9]{7,8}[A-Z a-z]").matcher(dni).matches()
    }
    private fun validarEmail():Boolean{
        return isValidEmailId(enlace.correo.text.toString().trim())
    }
    private fun isValidEmailId(email: String): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        enlace.InformarIncidencia.setOnClickListener {


            if(!validarDNI()){
                Toast.makeText(context, "El DNI no es correcto", Toast.LENGTH_SHORT).show()

            }else if(enlace.nombre.text.isNullOrBlank()){
                Toast.makeText(context, "El nombre no puede estar vacio", Toast.LENGTH_SHORT).show()
            }else if (!validarEmail()) {

                Toast.makeText(context, "Formato de correo no valido", Toast.LENGTH_SHORT).show();

            }else if(!(enlace.movil.text.isNullOrBlank())){
                if (enlace.movil.text.length>=9){
                    navega()
                }else{
                    Toast.makeText(context, "El numero de telefono debe ser correcto", Toast.LENGTH_SHORT).show();
                }
            }else {
                navega()
            }
        }

    }
    private fun  navega(){
        val dni:String =enlace.dni.text.toString()
        findNavController().navigate(PortadaFragmentDirections.actionPortadaFragmentToDetalles(dni))
        insertar();
    }
    

    private fun insertar() {
        val nombre:String =enlace.nombre.text.toString()

        val dni:String =enlace.dni.text.toString()

        val correo:String =enlace.correo.text.toString()

        var movil:String =enlace.movil.text.toString()

        if (movil == ""){
            movil="0"
        }
        // Instantiate the RequestQueue.
        val url = "http://192.168.0.24/insertar.php"

        /*val jsonObject=JSONObject()
        jsonObject.put("nombre",nombre)
        jsonObject.put("dni",dni)
        jsonObject.put("correo",correo)
        jsonObject.put("movil",movil)*/

        // Request a string response from the provided URL.
        val jsonRequest = object: StringRequest(Request.Method.POST,url, { response ->
                Toast.makeText(context,"Usuario registrado con exito!", Toast.LENGTH_SHORT).show()
        },
            Response.ErrorListener { error: VolleyError? ->
                Toast.makeText(context,"error", Toast.LENGTH_SHORT).show()

            }){
            override fun getParams(): MutableMap<String, String> {
                val parametros=HashMap<String,String>()
                parametros.put("nombre",nombre)
                parametros.put("dni",dni)
                parametros.put("movil",movil)
                parametros.put("correo",correo)
                return parametros
            }


        }
        Volley.newRequestQueue(context).add(jsonRequest)

    }


}