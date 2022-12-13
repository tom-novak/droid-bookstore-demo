package com.tom_novak.droidbookstore

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tom_novak.droidbookstore.ui.bookdetail.BookDetailScreen
import com.tom_novak.droidbookstore.ui.booksearch.BookSearchScreen
import kotlinx.coroutines.CoroutineScope


@Composable
fun BookstoreNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    startDestination: String = BookstoreNavigation.BookstoreDemoDestinations.SEARCH_ROUTE,
    navActions: BookstoreNavigation.BookstoreDemoNavigationActions = remember(navController) {
        BookstoreNavigation.BookstoreDemoNavigationActions(navController)
    }
) {
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: startDestination

    NavHost(
        navController = navController, startDestination = startDestination, modifier = modifier
    ) {
        composable(
            BookstoreNavigation.BookstoreDemoDestinations.SEARCH_ROUTE
        ) { entry ->
            BookSearchScreen(onDetailClick = { book ->
                navActions.navigateToBookDetail(
                    bookId = book.isbn10 ?: book.isbn13 ?: "0"
                )
            })
        }
        composable(
            BookstoreNavigation.BookstoreDemoDestinations.BOOK_DETAIL_ROUTE
        ) { entry ->
            BookDetailScreen(onBack = { navController.popBackStack() })
        }
    }
}