package com.example.actividad_2_moviles_viu.models

import java.io.Serializable

data class Movie(
    var id: Int,
    var name: String,
    var release: String,
    var playtime: String,
    var description: String,
    var plot: String,
    var poster: String,
    var gif:String
):Serializable