package com.kingjinho.dontcallhim

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ActivityTest {

    @Test
    fun launch() {
        val activityScenario = ActivityScenario.launch(DontCallHimActivity::class.java)
        onView(withId(R.id.to_add_number)).check(matches(withText("전화번호 입력하기")))
        activityScenario.close()
    }
}