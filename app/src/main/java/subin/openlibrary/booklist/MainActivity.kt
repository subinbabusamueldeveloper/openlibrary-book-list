package subin.openlibrary.booklist

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import subin.openlibrary.booklist.core.ui.theme.OpenlibrarybooklistTheme
import subin.openlibrary.booklist.feature_book_list.presentation.BookListScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OpenlibrarybooklistTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding()
                ) {
                    BookListScreen()
                }
            }
        }
    }
}