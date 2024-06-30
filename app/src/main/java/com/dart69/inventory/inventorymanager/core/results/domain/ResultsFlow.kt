package com.dart69.inventory.inventorymanager.core.results.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

typealias ResultsFlow<T> = Flow<Results<T>>
typealias ResultsStateFlow<T> = StateFlow<Results<T>>
typealias MutableResultsStateFlow<T> = MutableStateFlow<Results<T>>

fun <T> resultsFlowOf(block: suspend () -> T): ResultsFlow<T> = flow<Results<T>> {
    emit(Results.Success(block()))
}.onStart {
    emit(Results.Loading)
}.catch { throwable ->
    emit(Results.Error(throwable))
}