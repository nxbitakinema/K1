package com.nx.keikakuv1.data


object CONSTANTs {

    const val NAVIGATION_SCREEN_S1 = "s1"
    const val NAVIGATION_SCREEN_S2 = "s2/{nxrId}"
    const val NAVIGATION_SCREEN_S3 = "s3/{nxrId}"
    const val NAVIGATION_SCREEN_S4 = "s4"
    const val NAVIGATION_ID = "nxrId"
    const val TABLE_NAME = "NXRtable"
    const val DATABASE_NAME = "NxrsDatabase"

    fun nxrDetailNavigation( nxrId : Int ) = "s2/$nxrId"
    fun nxrEditNavigation( nxrId : Int ) = "s3/$nxrId"


    fun List<Nx>?.orPlaceHolderList(): List<Nx> {
        fun placeHolderList(): List<Nx> {
            return listOf(
                Nx(
                    id = 0,
                    title = "",
                    text = "",
                    dateUpdated = ""
                )
            )
        }
        return if (this != null && this.isNotEmpty()){
            this
        } else placeHolderList()
    }

    val nxrDetailPlaceHolder = Nx(
        text = "Cannot find NXR details",
        id = 0,
        title = "Cannot find NXR details"
    )

}