package iestr.gag.grafo6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import iestr.gag.grafo6.databinding.FragmentDetallesBinding
import iestr.gag.grafo6.databinding.FragmentPantallaFinalBinding

class Detalles : Fragment() {

    lateinit var enlace: FragmentDetallesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
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
            Toast.makeText(context, "Volviendo", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_detalles_to_pantallaFinal)
        }
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Detalles().apply {
                arguments = Bundle().apply {
                }
            }
    }
}