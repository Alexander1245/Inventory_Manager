package com.dart69.inventory.inventorymanager.core.results.domain

sealed interface Results<out T> {

    sealed interface FinalResults<T>: Results<T>

    @JvmInline
    value class Success<T>(val data: T) : FinalResults<T>

    @JvmInline
    value class Error(val throwable: Throwable): FinalResults<Nothing>

    data object Loading : Results<Nothing>
}

val Results<*>.isLoading: Boolean get() = this is Results.Loading

val Results<*>.throwableOrNull: Throwable? get() = if(this is Results.Error) throwable else null

val <T> Results<T>.dataOrNull: T? get() = if(this is Results.Success) data else null