package com.example.prsencemanager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.prsencemanager.R
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ActionMode
import android.content.Intent
import android.widget.PopupMenu
import com.example.myapplication3.GroupesActivity
import kotlin.jvm.java


class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val btnPopup = findViewById<Button>(R.id.btnPopup)

        btnPopup.setOnClickListener {
            val popupMenu = PopupMenu(this, btnPopup)
            popupMenu.menuInflater.inflate(
                R.menu.menu_popup_dashboard, popupMenu.menu
            )

            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_ouvrir_groupes -> {
                        startActivity(
                            Intent(this, GroupesActivity::class.java)
                        )
                        true
                    }
                    R.id.action_informations -> {
                        AlertDialog.Builder(this)
                            .setTitle("PrésenceManager")
                            .setMessage(
                                "Menu contextuel flottant du tableau de bord."
                            )
                            .setPositiveButton("OK", null)
                            .show()
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }

        val btnCab = findViewById<Button>(R.id.btnCab)

        btnCab.setOnClickListener {
            if (actionMode == null) {
                actionMode = startSupportActionMode(cabCallback)
            }
        }


    }
    private  var actionMode: ActionMode? = null

    private val cabCallback = object : ActionMode.Callback {

        override fun onCreateActionMode(
            mode: ActionMode, menu: Menu
        ): Boolean {
            mode.menuInflater.inflate(R.menu.menu_cab_dashboard, menu)
            mode.title = "Actions contextuelles"
            return true
        }

        override fun onPrepareActionMode(
            mode: ActionMode, menu: Menu
        ): Boolean = false

        override fun onActionItemClicked(
            mode: ActionMode, item: MenuItem
        ): Boolean = when (item.itemId) {
            R.id.action_afficher -> {
                AlertDialog.Builder(this@DashboardActivity)
                    .setTitle("CAB")
                    .setMessage("Action Afficher exécutée.")
                    .setPositiveButton("OK", null)
                    .show()
                true
            }
            R.id.action_terminer -> {
                mode.finish()
                true
            }
            else -> false
        }

        override fun onDestroyActionMode(mode: ActionMode) {
            actionMode = null
        }
    }
}
