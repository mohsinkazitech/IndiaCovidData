package `in`.squaresoft.attendanceadmin.network

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET


interface Api {

    @GET("state_district_wise.json")
    fun getCovidDate(): Call<JsonObject>

}//end Api