package fr.uge.plutus.ui.ant

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

@Immutable
class AntShape(
    val default: RoundedCornerShape = RoundedCornerShape(10.dp),
) {

    fun copy(
        default: RoundedCornerShape = this.default,
    ): AntShape = AntShape(
        default
    )

}

internal val LocalShaping = staticCompositionLocalOf { AntShape() }