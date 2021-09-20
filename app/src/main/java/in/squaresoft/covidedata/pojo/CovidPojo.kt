package `in`.squaresoft.covidedata.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class CovidPojo
{

    var state:String?=null

    var district:String?=null

    var notes: String? = null

    var active: Int = 0

    var confirmed: Int =0

    var recovered: Int =0


    var Iactive: Int = 0

    var Iconfirmed: Int =0

    var Irecovered: Int =0

    var active_dist: Int? = null

    var confirmed_dist: Int? = null

    var recovered_dist: Int? = null

    var migratedother: Int? = null

    var deceased: Int? = null

    var delta: Delta? = null


}