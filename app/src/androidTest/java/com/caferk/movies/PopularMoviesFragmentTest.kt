package com.caferk.movies

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView.ViewHolder
import com.caferk.kotlinbasearchitecture.domain.entity.MovieResults
import com.caferk.movies.idlingresources.LoadingIdlingResource
import com.caferk.movies.model.RestApi
import com.caferk.movies.ui.main.MainActivity
import com.caferk.movies.util.gsonUpper
import com.caferk.movies.util.parseFileWith
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import retrofit2.Response
import javax.inject.Inject


@RunWith(AndroidJUnit4::class)
class PopularMoviesFragmentTest {

    companion object {
        private const val position5Title = "Coco"
        private const val position10Title = "John Wick"
        private const val position16Title = "There Be Dragons"
    }

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java, false, false)

    @Inject
    lateinit var restApi: RestApi

    @Inject
    lateinit var contextToString: String

    private lateinit var loadingIdlingResource: LoadingIdlingResource

    private val getPopularMoviesSuccessResponsePath = "get_popular_movies_success.json"
    private val language = "en"
    private val page = 1

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        val testApplication = InstrumentationRegistry
                .getTargetContext().applicationContext as BaseTestApplication
        testApplication.testApplicationComponent.inject(this)

        val popularMoviesResponse: MovieResults = getPopularMoviesSuccessResponsePath parseFileWith gsonUpper
        whenever(restApi.getPopularMovies(language, page)).thenReturn(Observable.just(Response.success(popularMoviesResponse)))

        activityTestRule.launchActivity(Intent())
        loadingIdlingResource = LoadingIdlingResource(activityTestRule.activity)
    }

    @Test
    fun testMovieDetailButtonIsShowing() {
        onView(withId(R.id.btn_create_new_note))
                .check(matches(isDisplayed()))
    }

    @Test
    fun testMovieRecyclerViewIsShowing() {
        onView(withId(R.id.frPopularMovies_rvMovies))
                .check(matches(isDisplayed()))
    }

    @Test
    fun testCheckRecyclerViewItem() {
        IdlingRegistry.getInstance().register(loadingIdlingResource)

        onView(withId(R.id.frPopularMovies_rvMovies))
                .perform(scrollToPosition<ViewHolder>(5))
                .check(matches(hasDescendant(withText(position5Title))))

        onView(withId(R.id.frPopularMovies_rvMovies))
                .perform(scrollToPosition<ViewHolder>(10))
                .check(matches(hasDescendant(withText(position10Title))))

        onView(withId(R.id.frPopularMovies_rvMovies))
                .perform(scrollToPosition<ViewHolder>(16))
                .check(matches(hasDescendant(withText(position16Title))))

        IdlingRegistry.getInstance().unregister(loadingIdlingResource)
    }
}