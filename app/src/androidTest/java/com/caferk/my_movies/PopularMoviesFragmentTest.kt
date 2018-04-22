package com.caferk.my_movies

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.caferk.kotlinbasearchitecture.domain.entity.MovieResults
import com.caferk.my_movies.ui.main.MainActivity
import com.caferk.my_movies.ui.main.popular.MovieDataModel
import com.caferk.my_movies.util.gsonUpper
import com.caferk.my_movies.util.parseFileWith
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject


@RunWith(AndroidJUnit4::class)
class PopularMoviesFragmentTest {

    val position5Title = "Coco"
    val position10Title = "John Wick"
    val position16Title = "There Be Dragons"

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java, false, false)

    @Inject
    lateinit var movieDataModel: MovieDataModel

    @Inject
    lateinit var contextToString: String

    val getPopularMoviesSuccessResponsePath = "get_popular_movies_success.json"
    val language = "en"
    val page = 1

    @Before
    fun setup() {
        val testApplication = InstrumentationRegistry
                .getTargetContext().applicationContext as BaseTestApplication
        testApplication.testApplicationComponent.inject(this)

        val popularMoviesResponse: MovieResults = getPopularMoviesSuccessResponsePath parseFileWith gsonUpper
        whenever(movieDataModel.getPopularMovies(language, page)).thenReturn(Observable.just(popularMoviesResponse))

        activityTestRule.launchActivity(Intent())
    }

    @Test
    fun movieDetailButtonIsShowing() {
        onView(withId(R.id.btn_create_new_note))
                .check(matches(isDisplayed()))
    }

    @Test
    fun movieRecyclerViewIsShowing() {
        onView(withId(R.id.frPopularMovies_rvMovies))
                .check(matches(isDisplayed()))
    }

    @Test
    fun checkRecyclerViewItem() {
        onView(withId(R.id.frPopularMovies_rvMovies))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
                .check(matches(hasDescendant(withText(position5Title))))

        onView(withId(R.id.frPopularMovies_rvMovies))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
                .check(matches(hasDescendant(withText(position10Title))))

        onView(withId(R.id.frPopularMovies_rvMovies))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(16))
                .check(matches(hasDescendant(withText(position16Title))))
    }

/*    @Test
    fun isDetailActivityShowing_whenMovieDetailButtonClick() {
        Intents.init()

        onView(withId(R.id.btn_create_new_note))
                .perform(click())

        intended(hasComponent(DetailActivity::class.java.name))
    }*/
}