package `in`.squaresoft.covidedata.model

import `in`.squaresoft.attendanceadmin.network.RetrofitClient
import `in`.squaresoft.covidedata.R
import `in`.squaresoft.covidedata.pojo.CovidPojo
import `in`.squaresoft.covidedata.presenter.CovidPresenter
import `in`.squaresoft.covidedata.view.CovidView
import android.content.Context
import android.util.Log
import com.google.gson.JsonObject
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

class CovidDataModel(var view:CovidView):CovidPresenter
{
    override fun showRecyclerView(context: Context) {
        view.showProgress()
        val api=RetrofitClient.getApi()
        val call=api.getCovidDate()
        call.enqueue(object :retrofit2.Callback<JsonObject>{
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                view.hideProgress()
                if(response.isSuccessful){
                    val jsonResponse = JSONObject(response.body().toString())

                    val jsonObjectKeys: Iterator<*> = jsonResponse.keys()
                    val list:ArrayList<CovidPojo> = ArrayList<CovidPojo>()

                    //int variable
                    var Iactive=0
                    var Iconfirm=0
                    var Irecovered=0

                    while (jsonObjectKeys.hasNext()) {

                        val name:String=jsonObjectKeys.next().toString()

                        val covidData=CovidPojo()
                        covidData.state=name

                        try{

                            val jsonDist=jsonResponse.getJSONObject(name).getJSONObject("districtData")

                            val jsonDistKey:Iterator<*> = jsonDist.keys()

                            while (jsonDistKey.hasNext())
                            {
                                val distName:String=jsonDistKey.next().toString()

                                covidData.district=distName
                                val jsonDetails: JSONObject = jsonResponse.getJSONObject(name).getJSONObject("districtData").getJSONObject(distName)

                                val active:String=jsonDetails.getString("active")
                                val confirm:String=jsonDetails.getString("confirmed")
                                val recover:String=jsonDetails.getString("recovered")

                                covidData.active = covidData.active + Math.abs(active.toInt())
                                covidData.confirmed= covidData.confirmed + confirm.toInt()
                                covidData.recovered= covidData.recovered + recover.toInt()

                            }

                        }catch (e:Exception){
                            e.printStackTrace()
                            val t=e.cause.toString()

                        }
                        //overall data
                        Iactive=Iactive+covidData.active
                        Iconfirm=Iconfirm+covidData.confirmed
                        Irecovered=Irecovered+covidData.recovered

                        list.add(covidData)
                    }

                    view.setData(list)
                    view.setAllData(Iactive.toString(),Iconfirm.toString(),Irecovered.toString())

                }else{
                    view.toast(context.getString(R.string.fail))
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                view.toast(t.message.toString())
                view.hideProgress()
            }

        })//end call

    }//end showRecyclerView

}