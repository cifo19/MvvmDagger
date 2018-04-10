package com.caferk.my_movies

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import com.caferk.my_movies.ui.detail.DetailActivity
import com.caferk.my_movies.ui.main.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PopularMoviesFragmentTest {

    @Rule
    @JvmField
    val activity = ActivityTestRule<MainActivity>(MainActivity::class.java, false, false)

    @Before
    fun setup() {

    }

    @Test
    fun movieDetailButtonIsShowing() {

        activity.launchActivity(Intent())

        onView(withId(R.id.btn_create_new_note))
            .check(matches(isDisplayed()))
    }

    @Test
    fun movieRecyclerViewIsShowing() {

        activity.launchActivity(Intent())

        onView(withId(R.id.frPopularMovies_rvMovies))
            .check(matches(isDisplayed()))
    }

    @Test
    fun isDetailActivityShowing_whenMovieDetailButtonClick() {
        Intents.init()
        activity.launchActivity(Intent())

        onView(withId(R.id.btn_create_new_note))
            .perform(click())

        intended(hasComponent(DetailActivity::class.java.name))
    }
}