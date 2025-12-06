package subin.openlibrary.booklist.core.network

import retrofit2.HttpException
import subin.openlibrary.booklist.core.common.Resource
import java.io.IOException

suspend inline fun <T> safeApiCall(
    crossinline apiCall: suspend () -> T
): Resource<T> {
    return try {
        val result = apiCall()
        Resource.Success(result)
    } catch (e: HttpException) {
        Resource.Error(
            message = "Network error: ${e.code()} ${e.message()}",
            throwable = e
        )
    } catch (e: IOException) {
        Resource.Error(
            message = "Check your internet connection",
            throwable = e
        )
    } catch (e: Exception) {
        Resource.Error(
            message = e.message ?: "Unexpected error",
            throwable = e
        )
    }
}