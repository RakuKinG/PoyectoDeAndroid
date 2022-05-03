package iestr.gag.grafo6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import iestr.gag.grafo6.databinding.FragmentJuegoBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [JuegoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class JuegoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var enlace:FragmentJuegoBinding

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
        enlace= FragmentJuegoBinding.inflate(inflater,container,false)
        return enlace.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        enlace.botonGanar.setOnClickListener {
/*
            val bundle= bundleOf("puntos" to enlace.campoPuntos.text.toString(),
                "nivel" to enlace.campoNivel.text.toString())
            findNavController().navigate(R.id.action_juegoFragment_to_ganaFragment,bundle)
*/
            findNavController().navigate(JuegoFragmentDirections.actionJuegoFragmentToGanaFragment(
                enlace.campoPuntos.text.toString(),enlace.campoNivel.text.toString()
            ))
        }
        enlace.botonPerder.setOnClickListener {
/*
            val bundle= bundleOf("mensaje" to enlace.campoMensaje.text.toString())
            findNavController().navigate(R.id.action_juegoFragment_to_pierdeFragment,bundle)
*/
            findNavController().navigate(JuegoFragmentDirections.actionJuegoFragmentToPierdeFragment(
                enlace.campoMensaje.text.toString()
            ))
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UnoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            JuegoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}