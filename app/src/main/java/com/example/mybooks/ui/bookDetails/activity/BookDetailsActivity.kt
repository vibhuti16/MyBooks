package com.example.mybooks.ui.bookDetails.activity

import android.os.Bundle
import com.example.mybooks.app.di.component.ApplicationComponent
import com.example.mybooks.app.di.subcomponent.bookDetails.activity.BookDetailsActivityModule
import com.example.mybooks.domain.model.books.Item
import com.example.mybooks.ui.base.BaseBackActivity
import com.example.mybooks.ui.bookDetails.fragment.BookDetailsFragment

class BookDetailsActivity : BaseBackActivity() {

    // INTENT DATA
    lateinit var mBook: Item

    override fun setupInjection(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(BookDetailsActivityModule(this)).injectTo(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getExtras()

        addFragmentToMainLayout(BookDetailsFragment.newInstance(mBook))
    }

    fun getExtras() {
        mBook = intent.extras!!.getSerializable(ARG_EXTRA_BOOK) as Item
    }

    companion object {
        val ARG_EXTRA_BOOK = "ARG_EXTRA_BOOK"
    }
}
