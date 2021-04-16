package com.example.mybooks.ui.viewmodel.books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mybooks.data.repository.BooksRepository
import com.example.mybooks.domain.model.Response
import com.example.mybooks.domain.model.books.BooksListResponse
import com.example.mybooks.domain.usecase.books.getBooksList.GetBooksListRequest
import com.example.mybooks.domain.usecase.books.getBooksList.GetBooksListUseCase
import com.example.mybooks.room.Books
import com.example.mybooks.utils.launchSilent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import androidx.lifecycle.*
import com.example.mybooks.room.LocalBooksRepository
import kotlinx.coroutines.launch

class BooksViewModel
    @Inject
    constructor(private val getBooksListUseCase: GetBooksListUseCase?,
                private val coroutineContext: CoroutineContext?)
    : ViewModel() {

    private var job: Job = Job()
    private lateinit var repository: LocalBooksRepository

    fun setRepository(localBooksRepository: LocalBooksRepository){
        repository = localBooksRepository
        allBooks = repository.allBooks.asLiveData()
    }

    var getBooksListStateLiveData = MutableLiveData<GetBooksListState>()

    init {
    }

    /**
     * GET CURRENT LOCATION
     */
    fun getBooksList(query: String) = launchSilent(coroutineContext!!, job) {
        getBooksListStateLiveData.postValue(LoadingGetBooksListState())

        val request = GetBooksListRequest(query)
        val response = getBooksListUseCase!!.execute(request)
        processCurrentLocationResponse(response)
    }

    fun processCurrentLocationResponse(response: Response<BooksListResponse>){
        if (response is Response.Success) {
            getBooksListStateLiveData.postValue(
                SuccessGetBooksListState(
                    response
                )
            )
        } else if (response is Response.Error) {
            getBooksListStateLiveData.postValue(
                ErrorGetBooksListState(
                    response
                )
            )

        }
    }

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

     lateinit var allBooks: LiveData<List<Books>>

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(word: Books) = viewModelScope.launch {
        repository!!.insert(word)
    }

    fun deleteAllBooks() = viewModelScope.launch {
        repository!!.deleteAll()
    }

}


