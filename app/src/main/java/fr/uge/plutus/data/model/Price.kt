package fr.uge.plutus.data.model

data class Price(

    val currency: String,
    val amount: Double

) {
    override fun toString(): String {
        return "$currency $amount"
    }
}