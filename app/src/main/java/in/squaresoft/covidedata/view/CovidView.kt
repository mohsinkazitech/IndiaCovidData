package `in`.squaresoft.covidedata.view

import `in`.squaresoft.covidedata.pojo.CovidPojo
import android.view.View
import android.widget.Toast

interface CovidView
{
    fun showProgress()

    fun hideProgress()

    fun toast(message:String)

    fun setData(data:ArrayList<CovidPojo>)

    fun setAllData(active:String,confirm:String,recovered:String)

}