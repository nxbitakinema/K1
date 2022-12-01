package com.nx.keikakuv1

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nx.keikakuv1.data.AppViewModel
import com.nx.keikakuv1.data.CONSTANTs.NAVIGATION_ID
import com.nx.keikakuv1.data.CONSTANTs.NAVIGATION_SCREEN_S1
import com.nx.keikakuv1.data.CONSTANTs.NAVIGATION_SCREEN_S2
import com.nx.keikakuv1.data.CONSTANTs.NAVIGATION_SCREEN_S3
import com.nx.keikakuv1.data.CONSTANTs.NAVIGATION_SCREEN_S4
import com.nx.keikakuv1.data.ViewModelFactory
import com.nx.keikakuv1.screen.S1
import com.nx.keikakuv1.screen.S2
import com.nx.keikakuv1.screen.S3
import com.nx.keikakuv1.screen.S4
import com.nx.keikakuv1.ui.KEIKAKUv1Theme

class MainActivity : ComponentActivity() {

    private lateinit var xxx : AppViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        xxx = ViewModelFactory(Apps.getDao()).create(AppViewModel::class.java)

        setContent {

            KEIKAKUv1Theme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = NAVIGATION_SCREEN_S1
                ) {

                    /// S C R E E N - 01 ////////////////////////////////////////////// l i s t ///
                    composable( NAVIGATION_SCREEN_S1 ) { S1 ( navController, xxx ) }


                    /// S C R E E N - 02 ////////////////////////////////////////// d e t a i l ///
                    composable( NAVIGATION_SCREEN_S2,
                        arguments = listOf( navArgument(NAVIGATION_ID) { type = NavType.IntType } )
                    ) { backStackEntry ->
                        backStackEntry.arguments?.getInt(NAVIGATION_ID)
                            ?.let { S2 ( nxrId = it, navController, xxx ) }
                    }


                    /// S C R E E N - 03 ////////////////////////////////////////////// e d i t ///
                    composable( NAVIGATION_SCREEN_S3,
                        arguments = listOf( navArgument(NAVIGATION_ID) { type = NavType.IntType } )
                    ) { backStackEntry ->
                        backStackEntry.arguments?.getInt(NAVIGATION_ID)
                            ?.let { S3 ( nxrId = it, navController , xxx ) }
                    }


                    /// S C R E E N - 04 ////////////////////////////////////////// c r e a t e ///
                    composable( NAVIGATION_SCREEN_S4 ) { S4 ( navController, xxx ) }

                }
            }
        }
    }
}