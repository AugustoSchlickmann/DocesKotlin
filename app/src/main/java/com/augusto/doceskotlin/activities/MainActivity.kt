package com.augusto.doceskotlin.activities

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.augusto.doceskotlin.EDITAR_DOCES
import com.augusto.doceskotlin.PROCURAR_ENCOMENDAS_DA_SEMANA
import com.augusto.doceskotlin.PROCURAR_ENCOMENDAS_POR_DATA
import com.augusto.doceskotlin.PROCURAR_ENCOMENDAS_POR_NOME_CLIENTE
import com.augusto.doceskotlin.PROCURAR_PROXIMAS_ENCOMENDAS
import com.augusto.doceskotlin.R
import com.augusto.doceskotlin.VER_DOCES_A_FAZER
import com.augusto.doceskotlin.databinding.ActivityCarregandoBinding
import com.augusto.doceskotlin.fragments.CadastrarEncomendaFragment
import com.augusto.doceskotlin.fragments.InicioFragment
import com.augusto.doceskotlin.fragments.ListaDocesFragment
import com.augusto.doceskotlin.fragments.PerfilFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    DrawerLayout.DrawerListener {

    var fragmentSelecionado: Fragment? = null
    var drawerLayout: DrawerLayout? = null
    var navigationView: NavigationView? = null
    var container: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawer_base)

        container = findViewById(R.id.frameLayout)
        val toolbar: Toolbar = findViewById(R.id.toolBar)
        setSupportActionBar(toolbar)
        navigationView = findViewById(R.id.drawerLayoutNavigationView)
        navigationView!!.setNavigationItemSelectedListener(this)
        drawerLayout = findViewById(R.id.drawerLayout)
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.menu_drawer_open,
            R.string.menu_drawer_close
        )


        drawerLayout!!.addDrawerListener(this)
        toggle.syncState()

        navigationView!!.setCheckedItem(R.id.nav_telaInicial)

        supportActionBar?.title = "Encomendas"
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, InicioFragment())
            .commitNow()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (navigationView!!.checkedItem!!.itemId != item.itemId) {
            when (item.itemId) {

                R.id.nav_telaInicial -> fragmentSelecionado = InicioFragment.newInstance(PROCURAR_PROXIMAS_ENCOMENDAS)
                R.id.nav_docesAfazer -> fragmentSelecionado = ListaDocesFragment.newInstance(VER_DOCES_A_FAZER)
                R.id.nav_cadastrarEncomenda -> fragmentSelecionado = CadastrarEncomendaFragment()
                //R.id.nav_cadastrarBandejinha -> fragmentSelecionado = CadastrarEncomendaFragment()
                R.id.nav_fecharSemana -> fragmentSelecionado = InicioFragment.newInstance(PROCURAR_ENCOMENDAS_DA_SEMANA)
                //R.id.nav_procurarPorData -> fragmentSelecionado = InicioFragment.newInstance(PROCURAR_ENCOMENDAS_POR_DATA)
                //R.id.nav_procurarPorCliente -> fragmentSelecionado = InicioFragment.newInstance(PROCURAR_ENCOMENDAS_POR_NOME_CLIENTE)
                R.id.nav_doces -> fragmentSelecionado = ListaDocesFragment.newInstance(EDITAR_DOCES)
                //R.id.nav_clientes ->
                R.id.nav_perfil -> fragmentSelecionado = PerfilFragment()

            }

            container!!.removeAllViews()
            container!!.addView(ActivityCarregandoBinding.inflate(layoutInflater).root)

        }
        drawerLayout!!.close()
        return true
    }

    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {

    }

    override fun onDrawerOpened(drawerView: View) {

    }

    override fun onDrawerClosed(drawerView: View) {
        if (fragmentSelecionado != null) {
            supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragmentSelecionado!!).commitNow()
            fragmentSelecionado = null

        }
    }

    override fun onDrawerStateChanged(newState: Int) {

    }

}