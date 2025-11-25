package com.harukadev.studyinglifecycle.core.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext

/**
 * Observes a [Flow] of one-time UI events in a lifecycle-aware manner and dispatches each
 * event to the provided [onEvent] callback.
 *
 * This function is intended for transient UI actions such as navigation, toasts, snackbars,
 * dialogs, or any side-effect that should not be replayed after configuration changes.
 * It collects the given [events] flow only when the current lifecycle is at least in
 * [minActiveState] (default = `STARTED`), automatically stopping when the UI goes to the
 * background and resuming when it becomes visible again.
 *
 * This prevents:
 *
 * - Events being handled while the UI is not visible
 * - Duplicate event execution caused by recomposition
 * - Unnecessary work when the composable is not active
 *
 * Optional controls:
 * - [debounceMillis]: Applies a debounce to throttle event emissions (useful to avoid
 *   repeated navigations triggered by fast taps).
 * - [filter]: Allows selectively processing events without modifying the ViewModel.
 *
 * Event processing is executed on the **main immediate dispatcher**, ensuring that all
 * UI-related side-effects occur safely and with minimal overhead.
 *
 * ### Example Usage
 * ```
 * ObserveAsEvents(viewModel.events) { event ->
 *     when (event) {
 *         is UiEvent.ShowToast ->
 *             Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
 *
 *         is UiEvent.Navigate ->
 *             navController.navigate(event.route)
 *     }
 * }
 * ```
 *
 * ### When to Use
 * - Handling one-time UI events (navigation, snackbars, dialogs, etc.)
 * - Collecting `SharedFlow` or `Channel`-based UI events
 * - Preventing duplicate triggers on recomposition
 * - Ensuring collection stops when the UI is not visible
 *
 * ### When *Not* to Use
 * - Persistent UI state (use `StateFlow` or `uiState` instead)
 * - Data that should survive configuration changes or be displayed on screen
 *
 * @param T The type of event.
 * @param events The [Flow] emitting UI events, typically exposed by a ViewModel.
 * @param minActiveState The lifecycle state at which event collection becomes active.
 * @param debounceMillis Optional debounce duration (in milliseconds) before emitting events.
 * @param filter Optional predicate to ignore or allow certain events.
 * @param onEvent Suspended callback invoked for each emitted event.
 */
@OptIn(FlowPreview::class)
@Composable
fun <T> ObserveAsEvents(
    events: Flow<T>,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    debounceMillis: Long = 0L,
    filter: (T) -> Boolean = { true },
    onEvent: suspend (T) -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(lifecycleOwner, events) {
        lifecycleOwner.repeatOnLifecycle(minActiveState) {
            withContext(Dispatchers.Main.immediate) {
                var flow: Flow<T> = events

                if (debounceMillis > 0) {
                    flow = flow.debounce(debounceMillis)
                }

                flow
                    .filter(filter)
                    .onEach { onEvent(it) }
                    .collect()
            }
        }
    }
}
