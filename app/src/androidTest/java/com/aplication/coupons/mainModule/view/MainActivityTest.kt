package com.aplication.coupons.mainModule.view


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.aplication.coupons.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        val textInputEditText = onView(
            allOf(
                withId(R.id.etCoupon), withContentDescription("cupón"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tilCoupon),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textInputEditText.perform(replaceText("UDEMY_03"), closeSoftKeyboard())

        val materialButton = onView(
            allOf(
                withId(R.id.btnConsult), withText("Consultar"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        val textInputEditText2 = onView(
            allOf(
                withId(R.id.etDescription), withContentDescription("descripción"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tilDescription),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textInputEditText2.perform(replaceText("Test"), closeSoftKeyboard())

        val materialButton2 = onView(
            allOf(
                withId(R.id.btnCreate), withText("Crear cupón"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        materialButton2.perform(click())

        val snackbar = onView(withId(com.google.android.material.R.id.snackbar_text))
        snackbar.check(ViewAssertions.matches(withText("Cupón creado.")))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
