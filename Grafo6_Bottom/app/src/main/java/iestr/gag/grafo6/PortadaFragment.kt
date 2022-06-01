package iestr.gag.grafo6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import iestr.gag.grafo6.databinding.FragmentPortadaBinding
import java.util.regex.Matcher
import java.util.regex.Pattern

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PortadaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PortadaFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var enlace: FragmentPortadaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
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
        return isValidEmailId(enlace.email.text.toString().trim())
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
            }else if (!validarEmail()){

                Toast.makeText(context, "Formato de email no valido", Toast.LENGTH_SHORT).show();


            }else {

                findNavController().navigate(R.id.action_portadaFragment_to_detalles)
            }
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CeroFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PortadaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}