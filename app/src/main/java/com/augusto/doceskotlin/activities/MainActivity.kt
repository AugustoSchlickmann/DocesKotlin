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
import com.augusto.doceskotlin.ARG_PARAM_CLIENTE_PARCELABLE
import com.augusto.doceskotlin.CADASTRANDO_ENCOMENDA_BANDEJINHA
import com.augusto.doceskotlin.FRAGMENT_CADASTRAR_ENCOMENDA_COM_CLIENTE
import com.augusto.doceskotlin.MAIN_ACTIVITY_QUAL_FRAGMENTO_CRIAR
import com.augusto.doceskotlin.PROCURAR_ENCOMENDAS_DO_CLIENTE
import com.augusto.doceskotlin.R
import com.augusto.doceskotlin.databinding.ActivityCarregandoBinding
import com.augusto.doceskotlin.fragments.CadastrarEncomendaFragment
import com.augusto.doceskotlin.fragments.inicio.InicioFragmentEncomendasCliente
import com.augusto.doceskotlin.fragments.inicio.InicioFragmentEncomendasData
import com.augusto.doceskotlin.fragments.inicio.InicioFragmentEncomendasSemana
import com.augusto.doceskotlin.fragments.inicio.InicioFragmentEncomendasProximas
import com.augusto.doceskotlin.fragments.ListaClientesFragment
import com.augusto.doceskotlin.fragments.PerfilFragment
import com.augusto.doceskotlin.fragments.listaDeDoces.DocesAFazerFragment
import com.augusto.doceskotlin.fragments.listaDeDoces.EditarDocesFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener {

    private var param: Int? = null
    private var fragmentSelecionado: Fragment? = null
    private var drawerLayout: DrawerLayout? = null
    private var navigationView: NavigationView? = null
    private var container: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawer_base)

        container = findViewById(R.id.frameLayout)
        val toolbar: Toolbar = findViewById(R.id.toolBar)
        setSupportActionBar(toolbar)
        navigationView = findViewById(R.id.drawerLayoutNavigationView)
        navigationView!!.setNavigationItemSelectedListener(this)
        drawerLayout = findViewById(R.id.drawerLayout)
        val toggle =
            ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.menu_drawer_open, R.string.menu_drawer_close)
        drawerLayout!!.addDrawerListener(this)
        toggle.syncState()

        param = intent.getIntExtra(MAIN_ACTIVITY_QUAL_FRAGMENTO_CRIAR, 0)

        when (param) {
            0 -> {
                supportActionBar?.title = "Encomendas"
                navigationView!!.setCheckedItem(R.id.nav_telaInicial)
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, InicioFragmentEncomendasProximas()).commitNow()
            }

            FRAGMENT_CADASTRAR_ENCOMENDA_COM_CLIENTE -> {
                supportActionBar?.title = "Cadastrar Encomenda"
                navigationView!!.setCheckedItem(R.id.nav_cadastrarEncomenda)
                supportFragmentManager.beginTransaction().replace(
                    R.id.frameLayout,
                    CadastrarEncomendaFragment.cadastrandoComCliente(
                        intent.getParcelableExtra(ARG_PARAM_CLIENTE_PARCELABLE)!!
                    )
                ).commitNow()
            }

            PROCURAR_ENCOMENDAS_DO_CLIENTE -> {
                supportActionBar?.title = "Encomendas do Cliente"
                navigationView!!.setCheckedItem(R.id.nav_telaInicial)
                supportFragmentManager.beginTransaction().replace(
                    R.id.frameLayout, InicioFragmentEncomendasCliente.encomendasDoCliente(
                        intent.getParcelableExtra(ARG_PARAM_CLIENTE_PARCELABLE)!!
                    )
                ).commitNow()
            }
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (navigationView!!.checkedItem!!.itemId != item.itemId) {
            when (item.itemId) {

                R.id.nav_telaInicial -> {
                    supportActionBar?.title = "Encomendas"
                    fragmentSelecionado = InicioFragmentEncomendasProximas()
                }

                R.id.nav_docesAfazer -> {
                    supportActionBar?.title = "Doces a Fazer"
                    fragmentSelecionado = DocesAFazerFragment()
                }

                R.id.nav_cadastrarEncomenda -> {
                    supportActionBar?.title = "Cadastrar Encomenda"
                    fragmentSelecionado = CadastrarEncomendaFragment()
                }

                R.id.nav_cadastrarBandejinha -> {
                    supportActionBar?.title = "Bandejinhas"
                    fragmentSelecionado =
                        CadastrarEncomendaFragment.cadastrandoBandejinha(CADASTRANDO_ENCOMENDA_BANDEJINHA)
                }

                R.id.nav_fecharSemana -> {
                    supportActionBar?.title = "Fechar Semana"
                    fragmentSelecionado = InicioFragmentEncomendasSemana()
                }

                R.id.nav_procurarPorData -> {
                    supportActionBar?.title = "Selecione uma Data"
                    fragmentSelecionado = InicioFragmentEncomendasData()
                }

                R.id.nav_procurarPorCliente -> {
                    supportActionBar?.title = "Digite um Nome"
                    fragmentSelecionado = InicioFragmentEncomendasCliente()
                }

                R.id.nav_doces -> {
                    supportActionBar?.title = "Doces"
                    fragmentSelecionado = EditarDocesFragment()
                }

                R.id.nav_clientes -> {
                    supportActionBar?.title = "Clientes"
                    fragmentSelecionado = ListaClientesFragment()
                }

                R.id.nav_perfil -> {
                    supportActionBar?.title = "Perfil"
                    fragmentSelecionado = PerfilFragment()
                }

            }
            container!!.removeAllViews()
            container!!.addView(ActivityCarregandoBinding.inflate(layoutInflater).root)

        }
        drawerLayout!!.close()
        return true

    }

    override fun onDrawerClosed(drawerView: View) {
        if (fragmentSelecionado != null) {
            supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragmentSelecionado!!).commitNow()
            fragmentSelecionado = null

        }
    }

    //SOLID Interface Segregation???
    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {}

    override fun onDrawerOpened(drawerView: View) {}

    override fun onDrawerStateChanged(newState: Int) {}

}