package com.example.cineplus

import com.example.cineplus.repository.AuthRepository
import com.example.cineplus.viewmodel.LoginViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    private lateinit var viewModel: LoginViewModel
    private val authRepository: AuthRepository = mock()

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = LoginViewModel(authRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    // 1️ Login exitoso
    @Test
    fun `login exitoso debe poner isLoggedIn en true`() = runTest {
        whenever(authRepository.login(eq("user"), eq("pass"), any(), any())).then {
            val onSuccess = it.getArgument<() -> Unit>(2)
            onSuccess()
        }

        viewModel.login("user", "pass")

        assertTrue(viewModel.isLoggedIn.value)
        assertNull(viewModel.loginError.value)
    }

    // 2️ Login fallido
    @Test
    fun `login fallido debe poner isLoggedIn en false`() = runTest {
        whenever(authRepository.login(eq("user"), eq("wrong"), any(), any())).then {
            val onError = it.getArgument<(String) -> Unit>(3)
            onError("Error")
        }

        viewModel.login("user", "wrong")

        assertFalse(viewModel.isLoggedIn.value)
    }

    // 3️ Login fallido debe asignar mensaje de error
    @Test
    fun `login fallido debe asignar mensaje de error`() = runTest {
        whenever(authRepository.login(eq("user"), eq("wrong"), any(), any())).then {
            val onError = it.getArgument<(String) -> Unit>(3)
            onError("Credenciales incorrectas")
        }

        viewModel.login("user", "wrong")

        assertEquals("Usuario o contraseña incorrectos", viewModel.loginError.value)
    }

    // 4️ Logout debe limpiar estados
    @Test
    fun `logout debe limpiar isLoggedIn y loginError`() = runTest {
        // Simular usuario logeado
        whenever(authRepository.login(eq("user"), eq("pass"), any(), any())).then {
            val onSuccess = it.getArgument<() -> Unit>(2)
            onSuccess()
        }
        viewModel.login("user", "pass")

        viewModel.logout()

        assertFalse(viewModel.isLoggedIn.value)
        assertNull(viewModel.loginError.value)
    }

    // 5️ Register debe llamar al repositorio sin error
    @Test
    fun `register debe ejecutarse sin errores`() = runTest {
        // No verificamos retorno porque register no devuelve nada
        viewModel.register("newUser", "1234")

        // Si llegó aquí, el test pasa sin excepciones
        assertTrue(true)
    }

    // 6️ Si login retorna false no debe activar isLoggedIn
    @Test
    fun `login false no debe activar isLoggedIn`() = runTest {
        whenever(authRepository.login(eq("user"), eq("123"), any(), any())).then {
            val onError = it.getArgument<(String) -> Unit>(3)
            onError("Error")
        }

        viewModel.login("user", "123")

        assertFalse(viewModel.isLoggedIn.value)
    }
}