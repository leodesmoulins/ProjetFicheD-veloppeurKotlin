package com.example.profildeveloppeur

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.profildeveloppeur.ui.profile.ProfileScreen
import com.example.profildeveloppeur.ui.theme.ProfilDeveloppeurTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfilDeveloppeurTheme {
                Scaffold { innerPadding ->
                    ProfileScreen(
                        name = "Léo D.",
                        role = "Développeur full-stack en alternance",
                        description = "Étudiant en Bachelor CDA au CESI, je conçois des applications web " +
                            "de bout en bout, du front Angular à l'API Spring Boot.",
                        skills = listOf("Angular", "Spring Boot", "PostgreSQL", "Docker", "GitHub Actions"),
                        email = "leo.dev@example.com",
                        availability = "Disponible pour une poursuite d'alternance",
                        onContactClick = {
                            // Plus tard : ouvrir l'application mail.
                        },
                        onAvailabilityClick = {
                            // Plus tard : afficher le détail de la disponibilité.
                        },
                        // innerPadding : espace réservé par le Scaffold (barres système).
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
