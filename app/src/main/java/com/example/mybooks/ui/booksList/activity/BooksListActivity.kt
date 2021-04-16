package com.example.mybooks.ui.booksList.activity

import android.os.Bundle
import com.example.mybooks.R
import com.example.mybooks.app.di.component.ApplicationComponent
import com.example.mybooks.app.di.subcomponent.booksList.activity.BooksListActivityModule
import com.example.mybooks.ui.base.BaseActivity
import com.example.mybooks.ui.booksList.fragment.BooksListFragment
import com.example.mybooks.ui.booksList.fragment.adapter.ItemBook

class BooksListActivity : BaseActivity(), BooksListFragment.OnFragmentListener {

    override var layoutId = R.layout.activity_books_list

    override fun setupInjection(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(BooksListActivityModule(this)).injectTo(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onClickBook(itemBook: ItemBook) {
        navigator.toBookDetailsActivity(itemBook.book, itemBook.itemView.findViewById(R.id.item_rv_book_iv_avatar))
    }
}
