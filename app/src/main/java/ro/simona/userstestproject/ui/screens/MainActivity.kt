package ro.simona.userstestproject.ui.screens

import android.annotation.SuppressLint
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
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import ro.simona.userstestproject.R
import ro.simona.userstestproject.ui.theme.UsersTestProjectTheme
import ro.simona.userstestproject.ui.viewmodels.UsersViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UsersTestProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UsersScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UsersScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.users_screen)) },
                colors = TopAppBarColors(
                    containerColor = colorResource(R.color.app_red),
                    scrolledContainerColor = colorResource(R.color.app_red),
                    navigationIconContentColor = colorResource(R.color.white),
                    titleContentColor = colorResource(R.color.white),
                    actionIconContentColor = colorResource(R.color.white)
                ),
                navigationIcon = {
                    IconButton(onClick = { /* Toggle drawer */ }) {
                        Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
                    }
                },
                actions = {
                    IconButton(onClick = { /* Search */ }) {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
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
        content = { UserScreenContent() }
    )
}


@Composable
private fun UserScreenContent(viewModel: UsersViewModel = koinViewModel()) {

    val uiState = viewModel.state.collectAsStateWithLifecycle()

    when {
        uiState.value.isLoading -> LoadingView()
        uiState.value.isError -> ErrorView(uiState.value.errorMessage ?: "Unknown error")
        else -> UsersList(users = uiState.value.users, onAdvance = viewModel::shouldAdvance)
    }

}