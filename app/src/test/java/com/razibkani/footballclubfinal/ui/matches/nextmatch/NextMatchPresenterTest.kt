package com.razibkani.footballclubfinal.ui.matches.nextmatch

import com.razibkani.footballclubfinal.data.DataManager
import com.razibkani.footballclubfinal.data.model.Event
import com.razibkani.footballclubfinal.data.model.FootballEventResponse
import com.razibkani.footballclubfinal.utils.TestCoroutineContextProvider
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class NextMatchPresenterTest {

    @Mock
    private
    lateinit var mvpView: NextMatchMvpView

    @Mock
    private
    lateinit var dataManager: DataManager

    private lateinit var presenter: NextMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = NextMatchPresenter(dataManager, TestCoroutineContextProvider())
    }

    @Test
    fun getEvents() {
        val events: List<Event>? = listOf()
        val response = FootballEventResponse(events)
        runBlocking {
            Mockito.`when`(dataManager.getFootballNextEvent("4328")).thenReturn(response)
        }

        presenter.attachView(mvpView)

        presenter.getEvents("4328")

        Mockito.verify(mvpView)?.showLoading()

        if (events != null && events.isNotEmpty()) {
            Mockito.verify(mvpView)?.updateDataEvents(events)
        } else {
            Mockito.verify(mvpView)?.showEmptyState()
        }

        Mockito.verify(mvpView)?.hideLoading()
    }
}