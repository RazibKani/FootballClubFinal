package com.razibkani.footballclubfinal.utils

import kotlinx.coroutines.experimental.Unconfined
import kotlin.coroutines.experimental.CoroutineContext

class TestCoroutineContextProvider : CoroutineContextProvider() {
    override val main: CoroutineContext = Unconfined
}