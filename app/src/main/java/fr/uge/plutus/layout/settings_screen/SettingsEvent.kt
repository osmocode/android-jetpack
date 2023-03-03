package fr.uge.plutus.layout.settings_screen


sealed class SettingsEvent {
    data class SetDarkTheme(val value: Boolean?) : SettingsEvent()
}
