package fr.uge.plutus.layout.transaction_overview

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.uge.plutus.data.repository.TransactionRepository
import javax.inject.Inject

@HiltViewModel
class TransactionOverviewViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository
) : ViewModel() {

    private val _state = mutableStateOf(TransactionOverviewState())
    val state: State<TransactionOverviewState> = _state

    init {

    }

}

