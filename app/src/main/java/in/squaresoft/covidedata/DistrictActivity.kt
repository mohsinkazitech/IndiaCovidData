package `in`.squaresoft.covidedata

import `in`.squaresoft.covidedata.adapter.CovidAdapter
import `in`.squaresoft.covidedata.adapter.DistrictAdapter
import `in`.squaresoft.covidedata.model.DistrictModel
import `in`.squaresoft.covidedata.pojo.CovidPojo
import `in`.squaresoft.covidedata.presenter.DistrictPresenter
import `in`.squaresoft.covidedata.view.CovidView
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DistrictActivity : AppCompatActivity(),CovidView
{

    override fun onBackPressed() {
        val intent=Intent(this,MainActivity::class.java)
        intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    lateinit var progressBar: ProgressBar
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?)
    {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_district)

        recyclerView=findViewById(R.id.district_recycler)
        progressBar=findViewById(R.id.district_progress)

        recyclerView.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        //val city = intent.getStringExtra("key")
        val state = intent.getStringExtra("state")
        Log.e("state "," ----------------------------> $state")

        val district:DistrictPresenter=DistrictModel(this)
        district.showRecyclerView(this,state!!)

    }//end

    override fun showProgress() {
        progressBar.visibility=View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility=View.INVISIBLE
    }

    override fun toast(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

    override fun setData(data: ArrayList<CovidPojo>) {
        val adapter= DistrictAdapter(data,this)
        recyclerView.adapter=adapter
    }

    override fun setAllData(active: String, confirm: String, recovered: String) {
        //no need to populate
    }

}