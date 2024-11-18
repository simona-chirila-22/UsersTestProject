package ro.simona.userstestproject.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ro.simona.userstestproject.data.api.ApiService
import ro.simona.userstestproject.data.models.UserDto

class UsersPagingSource(private val apiService: ApiService) : PagingSource<Int, UserDto>() {

    override fun getRefreshKey(state: PagingState<Int, UserDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserDto> {
        return try {
            val page = params.key ?: 1
            val response = apiService.getUsers(page = page).body()

            LoadResult.Page(
                data = response?.results ?: emptyList(),
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response?.results?.isEmpty() == true || page > 3) null else page.plus(
                    1
                ),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}