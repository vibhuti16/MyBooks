package com.example.mybooks.app.navigator

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import com.example.mybooks.domain.model.books.Item
import com.example.mybooks.ui.base.BaseActivity
import com.example.mybooks.ui.bookDetails.activity.BookDetailsActivity

class Navigator {

    var currentActivity: BaseActivity? = null

    private fun toDefaultActivity(activity: Class<*>, bundle: Bundle? = null, activityOptionsCompat: ActivityOptionsCompat? = null) {
        val intent = Intent(currentActivity, activity)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        currentActivity?.startActivity(intent, activityOptionsCompat?.toBundle())
    }


    /***********************************************************************************************
     *  ACTIVITIES
     **********************************************************************************************/

    fun toBookDetailsActivity(book: Item, viewIVTransition: View) {
        var bundle = Bundle()
        bundle.putSerializable(BookDetailsActivity.ARG_EXTRA_BOOK, book)

        // If we use Transitions there is a LeakMemory
        // https://github.com/square/leakcanary/issues/1081
        // IF not options are passed then the leak is not presented

        val p1 = Pair<View, String>(viewIVTransition, "transitionIVDetails")
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(currentActivity!!, p1)

        toDefaultActivity(BookDetailsActivity::class.java, bundle, options)

    }
}
