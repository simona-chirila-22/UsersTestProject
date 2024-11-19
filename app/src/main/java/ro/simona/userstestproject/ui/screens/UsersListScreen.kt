package ro.simona.userstestproject.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ro.simona.domain.entities.UserEntity
import ro.simona.presentation.utils.DateGenerator
import ro.simona.userstestproject.R

@Composable
fun UsersList(users: List<UserEntity>, onAdvance: () -> Unit, modifier: Modifier) {
    val listState = rememberLazyListState()

    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            lastVisibleItem >= users.size - 3
        }
    }

    LaunchedEffect(shouldLoadMore.value) {
        if (shouldLoadMore.value) {
            onAdvance()
        }
    }

    LazyColumn(
        modifier = modifier,
        state = listState
    ) {
        items(users) { userEntity ->
            UserItem(user = userEntity)
        }
    }

}

@Composable
fun UserItem(user: UserEntity) {
    Column {
        Row(
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .padding(15.dp)

        ) {
            AsyncImage(
                model = user.pictureUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .size(45.dp)
                    .clip(CircleShape)
            )

            Column(modifier = Modifier.padding(start = 15.dp)) {
                Text(
                    modifier = Modifier.padding(bottom = 3.dp),
                    fontSize = 20.sp,
                    text = user.name
                )

                Text(
                    text = stringResource(id = R.string.users_description, user.age, user.location)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(
                horizontalAlignment = Alignment.End
            ) {
                Row(modifier = Modifier.padding(bottom = 5.dp)) {
                    if (user.hasAttachment) {
                        Icon(
                            painter = painterResource(id = R.drawable.attachment_icon),
                            contentDescription = null,
                            modifier = Modifier
                                .size(25.dp)
                                .padding(end = 5.dp)
                                .align(alignment = Alignment.CenterVertically),
                            tint = colorResource(id = R.color.app_grey)
                        )
                    }

                    Text(
                        modifier = Modifier.align(alignment = Alignment.CenterVertically),
                        text = DateGenerator.formatDate(user.date),
                        fontSize = 12.sp
                    )
                }

                Icon(
                    painter = if (user.isFavorite) painterResource(id = R.drawable.favorite_set_icon) else painterResource(
                        id = R.drawable.favorite_icon
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp),
                    tint = colorResource(id = R.color.app_grey)
                )
            }
        }

        HorizontalDivider()
    }
}