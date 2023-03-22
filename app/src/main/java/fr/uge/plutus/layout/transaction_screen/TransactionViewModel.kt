package fr.uge.plutus.layout.transaction_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.uge.plutus.data.model.Tag
import fr.uge.plutus.data.repository.TagRepository
import fr.uge.plutus.data.repository.TransactionRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val tagRepository: TagRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(TransactionState())
    val state: State<TransactionState> = _state

    init {
        viewModelScope.launch {
            val action = savedStateHandle.get<String>("action")
            val type = savedStateHandle.get<String>("type")
            val id = savedStateHandle.get<Long>("id")

            _state.value = state.value.copy(
                action = action!!,
                type = type!!,
                id = id!!
            )


            if (id != -1L) transactionRepository.retrieveTransactionWithTag(id.toLong())
                ?.let { transactionWithTags ->
                    _state.value = state.value.copy(
                        transactionWithTags = transactionWithTags,
                        newTags = transactionWithTags.tags
                    )
                }

            tagRepository.retrieveAllTag(type).onEach { tags ->
                _state.value = state.value.copy(
                    tags = tags
                )
            }.launchIn(viewModelScope)
        }

    }

    fun onEvent(event: TransactionEvent) {
        when (event) {
            is TransactionEvent.TransactionUpdateDate -> viewModelScope.launch {
                _state.value = state.value.copy(
                    transactionWithTags = state.value.transactionWithTags.copy(
                        transaction = state.value.transactionWithTags.transaction.copy(
                            timestamp = event.timestamp
                        )
                    )
                )
            }

            is TransactionEvent.TransactionUpdateDesc -> viewModelScope.launch {
                _state.value = state.value.copy(
                    transactionWithTags = state.value.transactionWithTags.copy(
                        transaction = state.value.transactionWithTags.transaction.copy(
                            title = event.title,
                            description = event.desc
                        )
                    ),
                )
            }
            is TransactionEvent.TransactionUpdatePrice -> viewModelScope.launch {
                _state.value = state.value.copy(
                    transactionWithTags = state.value.transactionWithTags.copy(
                        transaction = state.value.transactionWithTags.transaction.copy(
                            price = event.price
                        )
                    )
                )
            }
            is TransactionEvent.TransactionUpdateTags -> viewModelScope.launch {
                _state.value = state.value.copy(
                    newTags = event.tags
                )
            }
            is TransactionEvent.TransactionSubmit -> viewModelScope.launch {
                when (state.value.action) {
                    "CREATE", "DUPLICATE" -> {
                        transactionRepository.createTransactionWithTags(
                            transaction = state.value.transactionWithTags.transaction.copy(
                                transactionId = null,
                                walletId = event.wallet,
                                type = state.value.type
                            ),
                            ttags = state.value.newTags
                        )
                    }
                    "UPDATE" -> {
                        transactionRepository.updateTransactionWithTag(
                            transaction = state.value.transactionWithTags.transaction,
                            ttags = state.value.newTags,
                            previousTags = state.value.transactionWithTags.tags,
                        )
                    }
                }
            }
            is TransactionEvent.TransactionTagCreate -> viewModelScope.launch {
                when (state.value.type) {
                    "DEBIT" -> {
                        tagRepository.createTag(
                            Tag(
                                tagId = null,
                                type = "DEBIT",
                                label = event.name
                            )
                        )
                    }
                    "CREDIT" -> {
                        tagRepository.createTag(
                            Tag(
                                tagId = null,
                                type = "CREDIT",
                                label = event.name
                            )
                        )
                    }
                    "TRANSFER" -> {
                        tagRepository.createTag(
                            Tag(
                                tagId = null,
                                type = "TRANSFER",
                                label = event.name
                            )
                        )
                    }
                }
            }
        }
    }

}