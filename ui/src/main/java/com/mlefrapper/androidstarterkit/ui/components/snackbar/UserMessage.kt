package com.mlefrapper.androidstarterkit.ui.components.snackbar

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed interface UserMessage {

    data class Text(val value: String) : UserMessage

    class StringResource(@StringRes val resId: Int, vararg val formatArgs: Any) : UserMessage

    companion object {

        fun from(value: String) = Text(value = value)

        fun from(@StringRes resId: Int, vararg formatArgs: Any) =
            StringResource(resId = resId, formatArgs = formatArgs)
    }
}

@Composable
fun UserMessage.asString() = when (this) {
    is UserMessage.Text -> value
    is UserMessage.StringResource -> stringResource(id = resId, formatArgs = formatArgs)
}
