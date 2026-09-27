package com.example.poo_1.mvvm.ui.view

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.poo_1.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MVVMActivityTest {

    // 1. Regla de JUnit 4 para inyectar dependencias simuladas/reales con Hilt en el emulador
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    /*
     * PRUEBA 1: Verifica que al iniciar la Activity se muestren las vistas de texto.
     */
    @Test
    fun whenActivityLaunches_showsQuoteAndAuthorTextViews() {
        // Given & When: Lanzamos la Activity en el contenedor de pruebas
        ActivityScenario.launch(MVVMActivity::class.java)

        // Then: Espresso verifica presencia en pantalla de los IDs de tu layout
        onView(withId(R.id.tvQuote)).check(matches(isDisplayed()))
        onView(withId(R.id.tvAuthor)).check(matches(isDisplayed()))
    }

    /*
     * PRUEBA 2: Simula el clic del usuario para solicitar una nueva frase aleatoria.
     */
    @Test
    fun whenUserClicksContainer_callsRandomQuote() {
        // Given
        ActivityScenario.launch(MVVMActivity::class.java)

        // When: Simula el tap del usuario en viewContainer (llamar a randomQuote())
        onView(withId(R.id.viewContainer)).perform(click())

        // Then: La interfaz debe continuar mostrando la frase actualizada
        onView(withId(R.id.tvQuote)).check(matches(isDisplayed()))
    }
}