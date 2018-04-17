package com.caferk.my_movies

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.caferk.kotlinbasearchitecture.domain.entity.MovieResults
import com.caferk.my_movies.di.DaggerTestApplicationComponent
import com.caferk.my_movies.di.Service1
import com.caferk.my_movies.di.Services
import com.caferk.my_movies.ui.main.MainActivity
import com.caferk.my_movies.ui.main.popular.MovieDataModel
import com.caferk.my_movies.util.gsonUpper
import com.caferk.my_movies.util.parseFileWith
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import javax.inject.Inject
import javax.inject.Named

@RunWith(AndroidJUnit4::class)
class PopularMoviesFragmentTest {

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java, false, false)

    @Mock
    lateinit var service1: Service1

    @Inject
    lateinit var movieDataModel: MovieDataModel

    @Inject
    lateinit var anyThing: String

    @Inject
    lateinit var s1: Service1

    val getPopularMoviesSuccessResponsePath = "get_popular_movies_success.json"
    val language = "en"
    val page = 1

    @Before
    fun setup() {

        MockitoAnnotations.initMocks(this)

        val testApplication = InstrumentationRegistry
            .getTargetContext().applicationContext as BaseTestApplication

        val testComponentBuilder =
            DaggerTestApplicationComponent.builder().application(testApplication)
                .services(Services(service1)).build()

        testApplication.setComponent(testComponentBuilder)
        testApplication.testApplicationComponent.inject(this)

        val popularMoviesResponse: MovieResults =
            getPopularMoviesSuccessResponsePath parseFileWith gsonUpper
        whenever(movieDataModel.getPopularMovies(language, page)).thenReturn(
            Observable.just(
                popularMoviesResponse
            )
        )

        assertEquals(anyThing, "test Me!")
        assertEquals(s1.toString(), "ME")

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

/*    @Test
    fun isDetailActivityShowing_whenMovieDetailButtonClick() {
        Intents.init()

        onView(withId(R.id.btn_create_new_note))
                .perform(click())

        intended(hasComponent(DetailActivity::class.java.name))
    }*/
}