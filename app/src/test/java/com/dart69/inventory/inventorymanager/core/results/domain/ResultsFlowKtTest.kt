package com.dart69.inventory.inventorymanager.core.results.domain

import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class ResultsFlowKtTest {

    @Test
    fun testSuccessResultsFlowEmitting() = runTest {
        val expected = listOf(Results.Loading, Results.Success(123))
        val actual = resultsFlowOf { expected.last().dataOrNull!! }.toList()
        assertEquals(expected, actual)
    }

    @Test
    fun testErrorResultsFlowEmitting() = runTest {
        val expected = listOf(Results.Loading, Results.Error(IllegalArgumentException()))
        val actual = resultsFlowOf { throw expected.last().throwableOrNull!! }.toList()
        assertEquals(expected, actual)
    }
}