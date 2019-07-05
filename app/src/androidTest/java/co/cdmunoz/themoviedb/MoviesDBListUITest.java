package co.cdmunoz.themoviedb;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.cdmunoz.themoviedb.list.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MoviesDBListUITest {
    @Rule
    public ActivityTestRule activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void listIsDisplayed() {
        onView(withId(R.id.movies_list)).check(matches(isDisplayed()));
        onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())));
    }

    @Test
    public void scrollToitem() {
        onView(withId(R.id.movies_list)).perform(RecyclerViewActions.scrollToPosition(4));
    }

    @Test
    public void scrollToItemAndClickOnIt() {
        onView(withId(R.id.movies_list)).perform(RecyclerViewActions.scrollToPosition(4));
        onView(withId(R.id.movies_list)).perform(click());
    }
}
