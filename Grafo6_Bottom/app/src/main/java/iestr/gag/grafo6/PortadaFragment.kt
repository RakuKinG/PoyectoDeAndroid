package iestr.gag.grafo6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import iestr.gag.grafo6.databinding.FragmentPortadaBinding

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
    fun soloNumeros():Boolean{
        var numero=""
        var miDNI=""
        val unoNueve = arrayOf("0","1","2","3","4","5","6","7","8","9")
        for (i in 0..enlace.dni.text.length){
            numero= enlace.dni.text.substring(i,i+1)
            for (j in 0..unoNueve.size){

                if(numero == unoNueve[j]){
                    miDNI+=unoNueve[j]
                }

            }
        }
        return miDNI.length == 8
    }

    fun letraDNI():String{
        var miLetra:String
        var resto=0
        val asignacionLetra = arrayOf("T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S","Q","V","H","L","C","K","E")
        var miDNI=Integer.parseInt(enlace.dni.text.substring(0,8))
        resto=miDNI%23;
        miLetra=asignacionLetra[resto];
        return miLetra;

    }

    fun validadorDNI():Boolean{

        var letra:String=""

        if (enlace.dni.text.length!=9 ) return false

        letra=enlace.dni.text.substring(8).uppercase()
        return soloNumeros() && letraDNI() == letra

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        enlace.InformarIncidencia.setOnClickListener {
            if(!validadorDNI()){
                Toast.makeText(context, "El DNI no es correcto", Toast.LENGTH_SHORT).show()

            }
            if(enlace.nombre.text.isNullOrBlank()){
                Toast.makeText(context, "El nombre no puede estar vacio", Toast.LENGTH_SHORT).show()
            }else if (enlace.dni.text.isNullOrBlank()){

                Toast.makeText(context, "El dni no puede estar vacio", Toast.LENGTH_SHORT).show()
            }else if (enlace.email.text.isNullOrBlank()){
                Toast.makeText(context, "El email no puede estar vacio", Toast.LENGTH_SHORT).show()
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