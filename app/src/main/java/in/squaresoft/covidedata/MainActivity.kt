package `in`.squaresoft.covidedata

import `in`.squaresoft.covidedata.adapter.CovidAdapter
import `in`.squaresoft.covidedata.model.CovidDataModel
import `in`.squaresoft.covidedata.pojo.CovidPojo
import `in`.squaresoft.covidedata.presenter.CovidPresenter
import `in`.squaresoft.covidedata.view.CovidView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(),CovidView
{

    lateinit var progressbar:ProgressBar
    lateinit var recyclerView: RecyclerView
    lateinit var active:TextView
    lateinit var confirm:TextView
    lateinit var recovered:TextView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressbar=findViewById(R.id.main_progressbar)
        recyclerView=findViewById(R.id.main_recycler)

        active=findViewById(R.id.main_txt_active)
        confirm=findViewById(R.id.main_txt_confirmed)
        recovered=findViewById(R.id.main_txt_recovered)

        val covidData: CovidPresenter =CovidDataModel(this)

        recyclerView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        covidData.showRecyclerView(this)

    }

    override fun showProgress(){
        progressbar.visibility=View.VISIBLE
    }//showProgress

    override fun hideProgress(){
        progressbar.visibility=View.INVISIBLE
    }//hideProgress

    override fun toast(message:String)
    {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }//end toast

    override fun setData(data: ArrayList<CovidPojo>) {
        val adapter=CovidAdapter(data,this)
        recyclerView.adapter=adapter
    }

    override fun setAllData(activeStr: String, confirmStr: String, recoveredStr: String) {
        active.text=activeStr
        confirm.text=confirmStr
        recovered.text=recoveredStr
    }

}//end MainActitvity