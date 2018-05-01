package com.caferk.movies

import android.app.Activity
import android.app.Instrumentation
import android.app.PendingIntent.getActivity
import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.Intents.intending
import android.support.test.espresso.intent.matcher.IntentMatchers.*
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView.ViewHolder
import com.caferk.kotlinbasearchitecture.domain.entity.Credits
import com.caferk.kotlinbasearchitecture.domain.entity.MovieResults
import com.caferk.kotlinbasearchitecture.domain.entity.PersonImages
import com.caferk.kotlinbasearchitecture.domain.entity.PersonMovieCredits
import com.caferk.movies.idlingresources.LoadingIdlingResource
import com.caferk.movies.model.RestApi
import com.caferk.movies.ui.detail.DetailActivity
import com.caferk.movies.ui.main.MainActivity
import com.caferk.movies.ui.main.popular.PopularMoviesFragment
import com.caferk.movies.util.gsonUpper
import com.caferk.movies.util.parseFileWith
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import retrofit2.Response
import javax.inject.Inject
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import android.support.test.espresso.Espresso.onView
import org.hamcrest.CoreMatchers.*


@RunWith(AndroidJUnit4::class)
class PopularMoviesFragmentTest {

    companion object {
        private const val position5Title = "Coco"
        private const val position10Title = "John Wick"
        private const val position16Title = "There Be Dragons"
        private const val firstMovieId = 337167
    }

    @Rule
    @JvmField
    val activityTestRule = IntentsTestRule<MainActivity>(MainActivity::class.java, false, false)

    @Inject
    lateinit var restApi: RestApi

    @Inject
    lateinit var contextToString: String

    private lateinit var loadingIdlingResource: LoadingIdlingResource

    private val getPopularMoviesSuccessResponsePath = "get_popular_movies_success.json"
    private val getCreditsByMovieSuccessResponsePath = "get_credits_by_movie_success.json"
    private val getPersonImagesSuccessResponsePath = "get_person_images_success.json"
    private val getPersonMovieCreditsSuccessResponsePath = "get_person_movie_credits_success.json"
    private val language = "en"
    private val page = 1

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        val testApplication = InstrumentationRegistry
                .getTargetContext().applicationContext as BaseTestApplication
        testApplication.testApplicationComponent.inject(this)

        val popularMoviesResponse: MovieResults = getPopularMoviesSuccessResponsePath parseFileWith gsonUpper
        val creditsByMovieResponse: Credits = getCreditsByMovieSuccessResponsePath parseFileWith gsonUpper
        val personImagesResponse: PersonImages = getPersonImagesSuccessResponsePath parseFileWith gsonUpper
        val personMovieCreditsResponse: PersonMovieCredits = getPersonMovieCreditsSuccessResponsePath parseFileWith gsonUpper

        whenever(restApi.getPopularMovies(language, page)).thenReturn(Observable.just(Response.success(popularMoviesResponse)))
        whenever(restApi.getCreditsByMovie(any())).thenReturn(Observable.just(Response.success(creditsByMovieResponse)))
        whenever(restApi.getPersonImages(any())).thenReturn(Observable.just(Response.success(personImagesResponse)))
        whenever(restApi.getPersonMovieCredits(any())).thenReturn(Observable.just(Response.success(personMovieCreditsResponse)))

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


    @Test
    fun pressButton_IsSendIntent_whenResponseReady() {
        IdlingRegistry.getInstance().register(loadingIdlingResource)
        onView(withId(R.id.btn_create_new_note))
                .perform(click())
        IdlingRegistry.getInstance().unregister(loadingIdlingResource)

        intended(allOf(hasComponent(DetailActivity::class.java.name), hasExtra(DetailActivity.MOVIE_ID, firstMovieId)))
    }

/*    @Test
    fun pressButton_IsSendIntent_whenResponseIsNotReady() {
        onView(withId(R.id.btn_create_new_note))
                .perform(click())

        onView(withText(R.string.empty_list_error)).inRoot(withDecorView(not(`is`(activityTestRule.activity.window.decorView)))).check(matches(isDisplayed()))
    }*/
}