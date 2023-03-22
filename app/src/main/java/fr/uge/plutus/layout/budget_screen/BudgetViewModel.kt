package fr.uge.plutus.layout.budget_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.uge.plutus.data.model.Tag
import fr.uge.plutus.data.repository.BudgetRepository
import fr.uge.plutus.data.repository.TagRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BudgetViewModel @Inject constructor(
    private val budgetRepository: BudgetRepository,
    private val tagRepository: TagRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(BudgetState())
    val state: State<BudgetState> = _state

    init {
        viewModelScope.launch {
            val type = savedStateHandle.get<String>("type")
            val wallet = savedStateHandle.get<Int>("wallet")

            _state.value = state.value.copy(
                type = type!!,
                wallet = wallet!!
            )


            if (wallet != -1) budgetRepository.retrieveBudget(wallet.toLong())
                ?.let { budgetAndTag ->
                    _state.value = state.value.copy(
                        budget = budgetAndTag.budget,
                    )
                }

            tagRepository.retrieveAllTag(type).onEach { tags ->
                _state.value = state.value.copy(
                    tags = tags
                )
            }.launchIn(viewModelScope)
        }
    }

    fun onEvent(event: BudgetEvent) {
        when (event) {
            is BudgetEvent.BudgetUpdateDateStart -> viewModelScope.launch {
                _state.value = state.value.copy(
                    budget = state.value.budget.copy(
                        dateStart = event.timestamp
                    )
                )
            }

            is BudgetEvent.BudgetUpdateDateEnd -> viewModelScope.launch {
                _state.value = state.value.copy(
                    budget = state.value.budget.copy(
                        dateEnd = event.timestamp
                    )
                )
            }

            is BudgetEvent.BudgetUpdatePrice -> viewModelScope.launch {
                _state.value = state.value.copy(
                    budget = state.value.budget.copy(
                        targetPrice = event.price
                    )
                )
            }

            is BudgetEvent.BudgetUpdateTag -> viewModelScope.launch {
                _state.value = state.value.copy(
                    newTag = event.tag,
                    budget = state.value.budget.copy(
                        tagId = event.tag[0].tagId!!
                    )
                )
            }

            is BudgetEvent.BudgetSubmit -> viewModelScope.launch {
                budgetRepository.createBudget(
                    budget = state.value.budget.copy(
                        budgetId = null,
                        walletId = event.wallet
                    )
                )
            }

            is BudgetEvent.BudgetTagCreate -> viewModelScope.launch {
                when (state.value.type) {
                    "DEBIT" -> {
                        tagRepository.createTag(
                            Tag(
                                tagId = null,
                                type = "DEBIT",
                                label = event.label
                            )
                        )
                    }
                    "CREDIT" -> {
                        tagRepository.createTag(
                            Tag(
                                tagId = null,
                                type = "CREDIT",
                                label = event.label
                            )
                        )
                    }
                    "TRANSFER" -> {
                        tagRepository.createTag(
                            Tag(
                                tagId = null,
                                type = "TRANSFER",
                                label = event.label
                            )
                        )
                    }
                }
            }
        }
    }
}