package ro.simona.userstestproject.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import ro.simona.presentation.viewmodels.UsersViewModel
import ro.simona.userstestproject.R
import ro.simona.userstestproject.ui.theme.UsersTestProjectTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UsersTestProjectTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text(text = stringResource(id = R.string.users_screen)) },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = colorResource(R.color.app_red),
                                titleContentColor = colorResource(R.color.white),
                                actionIconContentColor = colorResource(R.color.white),
                                navigationIconContentColor = colorResource(R.color.white),
                            ),
                            navigationIcon = {
                                IconButton(onClick = { /* Toggle drawer */ }) {
                                    Icon(
                                        imageVector = Icons.Filled.Menu,
                                        contentDescription = "Menu"
                                    )
                                }
                            },
                            actions = {
                                IconButton(onClick = { /* Search */ }) {
                                    Icon(
                                        imageVector = Icons.Filled.Search,
                                        contentDescription = "Search"
                                    )
                                }
                            }
                        )
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            shape = CircleShape,
                            containerColor = colorResource(R.color.app_red),
                            contentColor = colorResource(R.color.white),
                            onClick = { /* Perform action */ }) {
                            Icon(imageVector = Icons.Filled.Create, contentDescription = "Add")
                        }
                    },
                    content = { innerPadding ->
                        UserScreenContent(modifier = Modifier.padding(innerPadding))
                    }
                )
            }
        }
    }
}

@Composable
private fun UserScreenContent(viewModel: UsersViewModel = koinViewModel(), modifier: Modifier) {
    val uiState = viewModel.state.collectAsStateWithLifecycle()

    when {
        uiState.value.isLoading -> LoadingView()
        uiState.value.isError -> ErrorView(
            errorMessage = uiState.value.errorMessage
                ?: stringResource(id = R.string.unknown_error),
            onRetry = viewModel::shouldRetry
        )

        else -> UsersList(
            users = uiState.value.users,
            onAdvance = viewModel::shouldAdvance,
            modifier = modifier
        )
    }
}