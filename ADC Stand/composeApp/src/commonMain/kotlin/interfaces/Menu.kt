package interfaces

import androidx.compose.runtime.Composable

enum class MenuItem{
    START_PAGE, CONNECTION_PAGE, ADC_PAGE, KNOWLEDGE_PAGE, SETTINGS_PAGE
}

@Composable
expect fun drawVerticalMenu(onChange: (MenuItem) -> Unit)
@Composable
expect fun drawHorizontalMenu(onChange: (MenuItem) -> Unit)