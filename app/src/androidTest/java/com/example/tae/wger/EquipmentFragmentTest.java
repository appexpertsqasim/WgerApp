package com.example.tae.wger;

import com.example.tae.wger.fragments.EquipmentFragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by TAE on 29/10/2017.
 */

public class EquipmentFragmentTest {
    @Rule
    public FragmentTestRule<EquipmentFragment> equipmentFragmentTestRule = new FragmentTestRule<>(EquipmentFragment.class);

    @Before
    public void setUp() throws Exception{
        equipmentFragmentTestRule.launchActivity(null);
    }

    @Test
    public void instantiateFragment (){
        onView(withId(R.id.container)).check(matches(isDisplayed()));
    }
    @Test
    public void testRecyclerView(){

        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));
        //Espresso seems to need some time before it can register an item within the
        //Recycler View, thus the need to putt the thread to sleep after scrolling to a position
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //onView(withText("Barbell")).check(matches(isDisplayed()));
    }
}
