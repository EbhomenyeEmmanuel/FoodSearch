package com.esq.foodsearch.ui;


import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class VerifyUserTokenActivityTest {

    @Rule
    public ActivityTestRule<VerifyUserTokenActivity> mActivityTestRule = new ActivityTestRule<>(VerifyUserTokenActivity.class);

    @Test
    public void verifyUserTokenActivityTest() {
    }
}
