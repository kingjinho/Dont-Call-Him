package com.kingjinho.dontcallhim

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.kingjinho.dontcallhim.screen.main.ScreenMain
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class NavigationTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testNavigationToAddRemoveNumberScreen() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        val mainScenario = launchFragmentInContainer<ScreenMain>()

        mainScenario.onFragment {
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(it.requireView(), navController)
        }

        onView(ViewMatchers.withId(R.id.to_add_number)).perform(ViewActions.click())

        assertThat(navController.currentDestination?.id).isEqualTo(R.id.screenAddRemoveNumber)
    }
}