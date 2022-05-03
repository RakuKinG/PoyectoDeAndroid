package iestr.gag.grafo6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import iestr.gag.grafo6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val enlace:ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    lateinit var controladorNavegacion: NavController
    //lateinit var configuracionAppbar:AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(enlace.root)

        controladorNavegacion=(supportFragmentManager.findFragmentById(R.id.fragmento_navegacion) as NavHostFragment).navController
        setupActionBarWithNavController(controladorNavegacion);//,configuracionAppbar)
        enlace.barraInferior.setupWithNavController(controladorNavegacion)
    }

    override fun onSupportNavigateUp(): Boolean {
        return controladorNavegacion.navigateUp()
    }
}