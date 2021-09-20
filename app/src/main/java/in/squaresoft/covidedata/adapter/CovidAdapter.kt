package `in`.squaresoft.covidedata.adapter

import `in`.squaresoft.covidedata.DistrictActivity
import `in`.squaresoft.covidedata.R
import `in`.squaresoft.covidedata.pojo.CovidPojo
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.Serializable

class CovidAdapter(var adapterList:ArrayList<CovidPojo>,var context: Context):
    RecyclerView.Adapter<CovidAdapter.AdapterViewHolder>()
{
     override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CovidAdapter.AdapterViewHolder {
        val v=LayoutInflater.from(context).inflate(R.layout.dashboard_layout_file,parent,false)
        return AdapterViewHolder(v)
    }//end onCreateViewHolder

    override fun onBindViewHolder(holder: CovidAdapter.AdapterViewHolder, position: Int) {
        holder.title.text=adapterList[position].state

        holder.active.text= adapterList[position].active.toString()
        holder.confirmed.text=adapterList[position].confirmed.toString()
        holder.recovered.text=adapterList[position].recovered.toString()

        holder.layout.setOnClickListener {

            val intent=Intent(context,DistrictActivity::class.java)
            //intent.putExtra("key",adapterList[position].district.toString())
            intent.putExtra("state",adapterList[position].state.toString())
            context.startActivity(intent)
        }

    }//end onBindViewHolder

    override fun getItemCount(): Int {
        return adapterList.size
    }//end getItemCount

    class AdapterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var layout:LinearLayout=itemView.findViewById(R.id.dashboard_layout)

        var title:TextView=itemView.findViewById(R.id.dashboard_main_title)
        var active:TextView=itemView.findViewById(R.id.dashboard_txt_active)
        var confirmed:TextView=itemView.findViewById(R.id.dashboard_txt_confirmed)
        var recovered:TextView=itemView.findViewById(R.id.dashboard_txt_recovered)

    }//end



}//end class