package com.example.mybooks.ui.booksList.fragment


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mybooks.R
import com.example.mybooks.app.di.component.ApplicationComponent
import com.example.mybooks.app.di.subcomponent.booksList.fragment.BooksListFragmentModule
import com.example.mybooks.app.navigator.Navigator
import com.example.mybooks.domain.model.Response
import com.example.mybooks.domain.model.books.*
import com.example.mybooks.room.Books
import com.example.mybooks.ui.App
import com.example.mybooks.ui.base.BaseFragment
import com.example.mybooks.ui.base.snackBar
import com.example.mybooks.ui.base.toast
import com.example.mybooks.ui.booksList.fragment.adapter.BooksListRVAdapter
import com.example.mybooks.ui.booksList.fragment.adapter.ItemBook
import com.example.mybooks.ui.viewmodel.books.*
import kotlinx.android.synthetic.main.fragment_books_list.*
import javax.inject.Inject


class BooksListFragment : BaseFragment() {
    override var layoutId = R.layout.fragment_books_list
    @Inject
    lateinit var navigator: Navigator
    // View model
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var mBooksViewModel: BooksViewModel

    // RV Adapter
    private var mLayoutManager: LinearLayoutManager? = null
    private var mRvAdapter: BooksListRVAdapter? = null



    private var mCurrentQuery: String = ""

    private var mListener: OnFragmentListener? = null

    override fun setupInjection(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(BooksListFragmentModule(this)).injectTo(this)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mListener = context as OnFragmentListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflateView(inflater, container)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        prepareViewModel()

        configRecyclerView()
        configSearchView()

        mCurrentQuery = "Jane Austen"
        getData(mCurrentQuery)
        fragment_books_list_searchview.setQuery(mCurrentQuery,true)

        mBooksViewModel.allBooks.observe(this, Observer {booksList ->
            val items : ArrayList<Item> = ArrayList()
            for(books in booksList){
                val item = Item()
                val volumeInfo = VolumeInfo()
                volumeInfo.title = books.title
                volumeInfo.description = books.description
                volumeInfo.imageLinks = ImageLinks()
                volumeInfo.imageLinks!!.thumbnail = books.imageLinks
                volumeInfo.authors = mutableListOf()

                val saleInfo = SaleInfo()
                saleInfo.listPrice = Price()
                saleInfo.listPrice!!.amount = books.amount
                saleInfo.listPrice!!.currencyCode = books.currencyCode

                val accessInfo = AccessInfo()
                accessInfo.webReaderLink = books.webReaderLink

                item.volumeInfo = volumeInfo
                item.saleInfo = saleInfo
                item.accessInfo = accessInfo

                if(item.volumeInfo!!.title!=null && item.volumeInfo!!.title!="") {
                    items.add(item)
                }
            }

            if(items.size>1) {
                fragment_books_list_swipe_refresh_rv.visibility = View.VISIBLE
                fragment_books_list_layout_empty.visibility = View.GONE
                mRvAdapter?.setBookList(items)
                mRvAdapter?.notifyDataSetChanged()
            }else{
                fragment_books_list_swipe_refresh_rv.visibility = View.GONE
                fragment_books_list_layout_empty.visibility = View.VISIBLE
            }
        })
    }

    private fun prepareViewModel() {
        //Observer
        mBooksViewModel = ViewModelProviders.of(this, viewModelFactory).get(BooksViewModel::class.java)
        mBooksViewModel.setRepository((activity!!.application as App).repository)
        observeBooksViewModel()
    }

    private fun getData(query: String) {
        mBooksViewModel.getBooksList(query)
    }

    /****************************************************************************
     * OBSERVER
     ***************************************************************************/
    /** FORECAST OBSERVER **/
    private fun observeBooksViewModel() {
        mBooksViewModel.getBooksListStateLiveData.observe(this, mGetGetBooksListStateObserver)
    }

    private val mGetGetBooksListStateObserver = Observer<GetBooksListState> { state ->
        state?.let {
            when (state) {
                is SuccessGetBooksListState -> {
                    hideLoading()
                    hideError()
                    val success = it.response as Response.Success

                    showBooksList(success.data)

                }
                is LoadingGetBooksListState -> {
                    showLoading()
                    hideError()
                }
                is ErrorGetBooksListState -> {
                    hideLoading()
                    showError((it as ErrorGetBooksListState))
//                    showLocalBooksList()
                }
            }
        }
    }


    private fun showLoading(){
        fragment_books_list_swipe_refresh_rv.isRefreshing = true
    }

    private fun hideLoading(){
        fragment_books_list_swipe_refresh_rv.isRefreshing = false
    }

    private fun showError(errorGetForecastState: ErrorGetBooksListState) {
        val error = errorGetForecastState.response as Response.Error
        (activity as AppCompatActivity).snackBar("We are not able to connect at the moment. Please try again later", onClickListener = object : View.OnClickListener{
            override fun onClick(p0: View?) {
                getData(mCurrentQuery)
            }

        })
    }

    private fun hideError(){

    }

    private fun showBooksList(booksListResponse: BooksListResponse) {
        if (booksListResponse == null || booksListResponse.items.isNullOrEmpty()) {
            fragment_books_list_swipe_refresh_rv.visibility = View.GONE
            fragment_books_list_layout_empty.visibility = View.VISIBLE
            fragment_books_list_layout_tv_status.text = getString(R.string.books_search_not_found)
        } else {
            insertBooksList(booksListResponse)
//            fragment_books_list_swipe_refresh_rv.visibility = View.VISIBLE
//            fragment_books_list_layout_empty.visibility = View.GONE
//            mRvAdapter?.setBookList(booksListResponse.items)
//            mRvAdapter?.notifyDataSetChanged()
        }
    }

    private fun insertBooksList(booksListResponse: BooksListResponse){
        mBooksViewModel.deleteAllBooks()
        for (book in booksListResponse.items) {
            if(book.volumeInfo?.imageLinks == null){
                book.volumeInfo?.imageLinks = ImageLinks("","")
            }
            if(book.saleInfo?.listPrice == null){
                book.saleInfo?.listPrice = Price("","")
            }

            if(book.accessInfo?.webReaderLink == null){
                book.accessInfo?.webReaderLink = ""
            }
            val books = Books(book.id,book.volumeInfo?.title!!,book.volumeInfo?.description!!,book.volumeInfo?.imageLinks!!.thumbnail,book.volumeInfo?.getAuthorsString()!!,book.saleInfo?.listPrice!!.amount,book.saleInfo?.listPrice!!.currencyCode,book.accessInfo?.webReaderLink!!)
            mBooksViewModel.insert(books)
        }
    }

//    private fun showLocalBooksList(){
//
////        val booksList = mBooksViewModel.allBooks.value!!
//
//    }

    /**
     * CONFIG RV VIEW
     */
    fun configRecyclerView() {
        fragment_books_list_swipe_refresh_rv.isEnabled = false

        mLayoutManager = LinearLayoutManager(
            context,
            RecyclerView.VERTICAL, false
        )
        fragment_books_list_rv.layoutManager = mLayoutManager

        mRvAdapter = BooksListRVAdapter(
            listenerBookClicked = {
                mListener?.onClickBook(it)
                //navigator.toBookDetailsActivity(it.book, it.itemView.findViewById(R.id.item_rv_book_iv_avatar))
            }
        )

        fragment_books_list_rv.adapter = mRvAdapter
        /*fragment_books_list_swipe_refresh_rv.setOnRefreshListener {
            //getData()
        }*/
    }

    fun configSearchView() {
        fragment_books_list_searchview.requestFocus()
        fragment_books_list_searchview.isIconified = false
        fragment_books_list_searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                if (text.isNullOrEmpty()) {
                    toast(getString(R.string.books_search_error_empty_query))
                } else {
                    mCurrentQuery = text
                    getData(text)
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }

        })

        val closeButton = fragment_books_list_searchview.findViewById<ImageView>(R.id.search_close_btn)
        // Set on click listener
        closeButton.setOnClickListener(View.OnClickListener {
            mBooksViewModel.deleteAllBooks()
            fragment_books_list_searchview.setQuery("",true)
            fragment_books_list_swipe_refresh_rv.visibility = View.GONE
            fragment_books_list_layout_empty.visibility = View.VISIBLE

        })
    }

    interface OnFragmentListener{
        fun onClickBook(itemBook: ItemBook)
    }

}
