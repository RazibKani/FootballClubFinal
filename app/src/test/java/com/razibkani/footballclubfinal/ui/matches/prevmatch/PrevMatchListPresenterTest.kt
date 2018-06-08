package com.razibkani.footballclubfinal.ui.matches.prevmatch

import com.razibkani.footballclubfinal.data.DataManager
import com.razibkani.footballclubfinal.data.model.Event
import com.razibkani.footballclubfinal.data.model.FootballEventResponse
import com.razibkani.footballclubfinal.utils.TestCoroutineContextProvider
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class PrevMatchListPresenterTest {

    @Mock
    private
    lateinit var mvpView: PrevMatchListMvpView

    @Mock
    private
    lateinit var dataManager: DataManager

    private lateinit var presenter: PrevMatchListPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = PrevMatchListPresenter(dataManager, TestCoroutineContextProvider())
    }

    @Test
    fun getEvents() {
        val events: List<Event> = listOf()
        val response = FootballEventResponse(events)
        runBlocking {
            Mockito.`when`(dataManager.getFootballPrevEvent()).thenReturn(response)
        }

        presenter.attachView(mvpView)

        presenter.getEvents()

        verify(mvpView)?.showLoading()

        if (events.isNotEmpty()) {
            Mockito.verify(mvpView)?.updateData(events)
        } else {
            Mockito.verify(mvpView)?.showErrorMessage("Event Kosong")
        }

        verify(mvpView)?.hideLoading()
    }
}