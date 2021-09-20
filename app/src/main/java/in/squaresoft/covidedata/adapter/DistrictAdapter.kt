package `in`.squaresoft.covidedata.adapter

import `in`.squaresoft.covidedata.R
import `in`.squaresoft.covidedata.pojo.CovidPojo
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DistrictAdapter(var adapterList:ArrayList<CovidPojo>, var context: Context): RecyclerView.Adapter<DistrictAdapter.DistrictViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DistrictViewHolder {
        val v= LayoutInflater.from(context).inflate(R.layout.dashboard_layout_file,parent,false)
        return DistrictAdapter.DistrictViewHolder(v)
    }

    override fun onBindViewHolder(holder: DistrictViewHolder, position: Int) {

        holder.title.text=adapterList[position].district

        holder.active.text= adapterList[position].active.toString()
        holder.confirmed.text=adapterList[position].confirmed.toString()
        holder.recovered.text=adapterList[position].recovered.toString()
    }

    override fun getItemCount(): Int {
        return adapterList.size
    }


    class DistrictViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var layout: LinearLayout =itemView.findViewById(R.id.dashboard_layout)

        var title: TextView =itemView.findViewById(R.id.dashboard_main_title)
        var active: TextView =itemView.findViewById(R.id.dashboard_txt_active)
        var confirmed: TextView =itemView.findViewById(R.id.dashboard_txt_confirmed)
        var recovered: TextView =itemView.findViewById(R.id.dashboard_txt_recovered)


    }


}