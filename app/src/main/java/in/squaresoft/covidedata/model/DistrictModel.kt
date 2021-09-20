package `in`.squaresoft.covidedata.model

import `in`.squaresoft.attendanceadmin.network.RetrofitClient
import `in`.squaresoft.covidedata.R
import `in`.squaresoft.covidedata.pojo.CovidPojo
import `in`.squaresoft.covidedata.presenter.DistrictPresenter
import `in`.squaresoft.covidedata.view.CovidView
import android.content.Context
import android.util.Log
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

class DistrictModel(var view:CovidView):DistrictPresenter
{
    override fun showRecyclerView(context: Context,state:String) {
        view.showProgress()
        val api= RetrofitClient.getApi()
        val call=api.getCovidDate()
        call.enqueue(object :retrofit2.Callback<JsonObject>{
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                view.hideProgress()
                if(response.isSuccessful){
                    val jsonResponse = JSONObject(response.body().toString())


                    val list:ArrayList<CovidPojo> = ArrayList<CovidPojo>()

                        try{

                            val jsonDist=jsonResponse.getJSONObject(state).getJSONObject("districtData")

                            val jsonDistKey:Iterator<*> = jsonDist.keys()

                            while (jsonDistKey.hasNext())
                            {
                                val covidData=CovidPojo()

                                val distName:String=jsonDistKey.next().toString()
                                Log.e("District "," -----------------------------------> $distName")
                                covidData.district=distName
                                val jsonDetails: JSONObject = jsonResponse.getJSONObject(state).getJSONObject("districtData").getJSONObject(distName)

                                val active:String=jsonDetails.getString("active")
                                val confirm:String=jsonDetails.getString("confirmed")
                                val recover:String=jsonDetails.getString("recovered")

                                covidData.active =  Math.abs(active.toInt())
                                covidData.confirmed=  confirm.toInt()
                                covidData.recovered=  recover.toInt()
                                Log.e("Active "," -----------------------------------> $active")
                                Log.e("Confirm "," -----------------------------------> $confirm")
                                Log.e("Recover "," -----------------------------------> $recover")

                                list.add(covidData)
                            }

                        }catch (e:Exception){
                            e.printStackTrace()
                            val t=e.cause.toString()

                        }



                    view.setData(list)

                }else{
                    view.toast(context.getString(R.string.fail))
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                view.toast(t.message.toString())
                view.hideProgress()
            }

        })//end call
    }

}