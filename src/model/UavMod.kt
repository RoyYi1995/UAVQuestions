package model

import bean.Uav
import util.CheckUtil
import kotlin.Exception

class UavMod : IUavMod {
    var uavArray = arrayListOf<Uav>()
    var error = false//是否故障

    /**
     * 检测并添加无人机信息
     */
    override fun addUavInfo(msg: String) {
        val msgArray = msg.split(" ")
        val uav = Uav()
        //检测开头是否为4字符，后续是否为7字符
//        if (!CheckUtil.checkSize(msgArray)) {
//            error = true
//        }
        //如果之前已经故障，则该信息故障
        if (error||!CheckUtil.checkSizeError(msgArray)) {
            uav.error = true
            uavArray.add(uav)
            return
        }
        //添加相应信息
        try {
            uav.uav_id = msgArray[0]
            uav.x = msgArray[1].toInt()
            uav.y = msgArray[2].toInt()
            uav.z = msgArray[3].toInt()
            if (msgArray.size == 4) {
                uavArray.add(uav)
                return
            }
            uav._x = msgArray[4].toInt()
            uav._y = msgArray[5].toInt()
            uav._z = msgArray[6].toInt()
            if (!CheckUtil.checkCoordinate(uavArray.last(),uav)){
                error = true
            }
        } catch (e: Exception) {
            //如果封装错误，如信息不正确,String->int失败
            error = true
        }
        if (error)
            uav.error = error
        uavArray.add(uav)
    }

    override fun getIndexMsg(index: Int): String {
        val msg: String
        msg = try {
            val uav = uavArray[index]
            if (uav.error){
                return "Error $index"
            }
            "${uav.uav_id} $index ${uav.x + uav._x} ${uav.y + uav._y} ${uav.z + uav._z}"
        } catch (e: Exception) {
            "Cannot find $index"
        }
        return msg
    }

}