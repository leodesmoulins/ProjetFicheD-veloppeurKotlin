package com.example.profildeveloppeur.ui.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.profildeveloppeur.ui.theme.ProfilDeveloppeurTheme

/**
 * Écran principal : place la fiche dans l'écran.
 * Le padding passé ici à ProfileCard est un PADDING EXTERNE :
 * c'est le parent qui décide de l'espace autour de la fiche.
 */
@Composable
fun ProfileScreen(
    name: String,
    role: String,
    description: String,
    skills: List<String>,
    email: String,
    availability: String,
    onContactClick: () -> Unit,
    onAvailabilityClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        ProfileCard(
            name = name,
            role = role,
            description = description,
            skills = skills,
            email = email,
            availability = availability,
            onContactClick = onContactClick,
            onAvailabilityClick = onAvailabilityClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp) // padding externe
        )
    }
}

/**
 * Fiche de profil : regroupe toutes les sections.
 * Le padding de la Column intérieure est un PADDING INTERNE :
 * il espace le contenu par rapport au bord de la carte.
 */
@Composable
fun ProfileCard(
    name: String,
    role: String,
    description: String,
    skills: List<String>,
    email: String,
    availability: String,
    onContactClick: () -> Unit,
    onAvailabilityClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Column(modifier = Modifier.padding(20.dp)) { // padding interne
            ProfileHeader(name = name, role = role)

            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 12.dp)
            )

            SectionTitle(
                title = "Compétences",
                modifier = Modifier.padding(top = 20.dp, bottom = 8.dp)
            )
            for (skill in skills) {
                SkillItem(
                    skill = skill,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )
            }

            SectionTitle(
                title = "Contact",
                modifier = Modifier.padding(top = 20.dp, bottom = 8.dp)
            )
            SecondaryInfo(label = "E-mail", value = email)

            AvailabilityZone(
                text = availability,
                onClick = onAvailabilityClick,
                modifier = Modifier.padding(top = 20.dp)
            )

            ProfileActionButton(
                text = "Contacter",
                onClick = onContactClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
        }
    }
}

/** En-tête : nom + rôle. */
@Composable
fun ProfileHeader(
    name: String,
    role: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = name,
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = role,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

/** Titre de section, réutilisé pour « Compétences » et « Contact ». */
@Composable
fun SectionTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall,
        modifier = modifier
    )
}

/** Une compétence, affichée dans une petite pastille. */
@Composable
fun SkillItem(
    skill: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surface
    ) {
        Text(
            text = "• $skill",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp) // padding interne
        )
    }
}

/** Information secondaire de type « libellé : valeur ». */
@Composable
fun SecondaryInfo(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = "$label : $value",
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier
    )
}

/**
 * Zone cliquable personnalisée (une Surface, pas un Button).
 * On lui ajoute donc un rôle de bouton et une description
 * pour les lecteurs d'écran.
 */
@Composable
fun AvailabilityZone(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable(role = Role.Button, onClick = onClick)
            .semantics { contentDescription = "$text. Bouton, touchez pour plus de détails." },
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.padding(16.dp) // padding interne
        )
    }
}

/**
 * Bouton d'action. Il ne contient aucune logique :
 * il signale simplement le clic au parent via onClick.
 */
@Composable
fun ProfileActionButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(text = text)
    }
}

@Preview(name = "Full-stack disponible", showBackground = true)
@Composable
fun ProfileFullStackPreview() {
    ProfilDeveloppeurTheme(dynamicColor = false) {
        ProfileScreen(
            name = "Léo D.",
            role = "Développeur full-stack en alternance",
            description = "Étudiant en Bachelor CDA au CESI, je conçois des applications web " +
                "de bout en bout, du front Angular à l'API Spring Boot.",
            skills = listOf("Angular", "Spring Boot", "PostgreSQL", "Docker", "GitHub Actions"),
            email = "leo.dev@example.com",
            availability = "Disponible pour une poursuite d'alternance",
            onContactClick = {},
            onAvailabilityClick = {}
        )
    }
}

@Preview(name = "Android junior non disponible", showBackground = true)
@Composable
fun ProfileAndroidJuniorPreview() {
    ProfilDeveloppeurTheme(dynamicColor = false) {
        ProfileScreen(
            name = "Camille Martin",
            role = "Développeuse Android junior",
            description = "Passionnée par les interfaces mobiles, je découvre Jetpack Compose " +
                "et j'aime construire des écrans clairs et accessibles.",
            skills = listOf("Kotlin", "Jetpack Compose"),
            email = "camille.martin@example.com",
            availability = "Non disponible actuellement",
            onContactClick = {},
            onAvailabilityClick = {}
        )
    }
}
