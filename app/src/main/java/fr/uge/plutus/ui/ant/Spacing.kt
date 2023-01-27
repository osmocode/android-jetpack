package fr.uge.plutus.ui.ant

import androidx.compose.runtime.*
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Immutable
class AntSpacing(
    val default: Dp = 2.dp,
    val small: Dp = 4.dp,
    val middle: Dp = 8.dp,
    val large: Dp = 16.dp
) {

    fun copy(
        default: Dp = this.default,
        small: Dp = this.small,
        middle: Dp = this.middle,
        large: Dp = this.large
    ): AntSpacing = AntSpacing(
        default,
        small,
        middle,
        large
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AntSpacing) return false

        if (default !=  other.default) return false
        if (small != other.small) return false
        if (middle != other.middle) return false
        if (large != other.large) return false

        return true
    }

    //TODO
    override fun hashCode(): Int {
        return super.hashCode()
    }

    //TODO
    override fun toString(): String {
        return super.toString()
    }
}

internal val LocalSpacing = staticCompositionLocalOf { AntSpacing() }