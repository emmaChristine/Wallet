package com.demo.wallet.framework

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers

/**
 * Pull out events to separate class in Espresso tests.
 */
class Events {
    fun clickOnView(@IdRes viewId: Int) {
        Espresso.onView(ViewMatchers.withId(viewId)).perform(ViewActions.click())
    }
}