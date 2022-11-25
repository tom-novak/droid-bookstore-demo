package com.tom_novak.droidbookstore

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.tom_novak.droidbookstore.BookstoreNavigation.BookstoreDemoDestinationArgs.BOOK_ID_ARG
import com.tom_novak.droidbookstore.BookstoreNavigation.BookstoreDemoScreens.DETAIL_SCREEN
import com.tom_novak.droidbookstore.BookstoreNavigation.BookstoreDemoScreens.SEARCH_SCREEN

class BookstoreNavigation {

    private object BookstoreDemoScreens {
        const val SEARCH_SCREEN = "search"
        const val DETAIL_SCREEN = "detail"
    }

    object BookstoreDemoDestinationArgs {
        const val BOOK_ID_ARG = "bookId"
    }

    object BookstoreDemoDestinations {
        const val SEARCH_ROUTE = SEARCH_SCREEN
        const val BOOK_DETAIL_ROUTE = "$DETAIL_SCREEN/{$BOOK_ID_ARG}"
    }

    class BookstoreDemoNavigationActions(private val navController: NavHostController) {
        fun navigateToBookSearch() {
            navController.navigate(BookstoreDemoDestinations.SEARCH_ROUTE) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }

        fun navigateToBookDetail(bookId: String) {
            navController.navigate("$DETAIL_SCREEN/$bookId")
        }
    }
}