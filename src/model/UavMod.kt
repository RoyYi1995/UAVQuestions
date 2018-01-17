package model

import bean.UavMsg
import util.CheckUtil
import kotlin.Exception

object UavMod : IUavMod {
    private var uavMsgArray = arrayListOf<UavMsg>()
    private var error = false//是否故障
    /**
     * 检测并添加无人机信息
     * @param msg 信息
     */
    override fun addUavInfo(msg: String) {
        val msgArray = msg.split(" ")
        val uavMsg = UavMsg()

        //判断每次输入id是否相同，注意第一条信息过来时uavMsgArray.last()为null
        if (!CheckUtil.first){
            if (!CheckUtil.checkUavId(uavMsgArray.last().id,msgArray[0])){
                uavMsg.error = true
                uavMsgArray.add(uavMsg)
                return
            }
        }
        //如果之前已经故障，则该信息故障, 并且检测开头是否为4字符，后续是否为7字符
        if (error||!CheckUtil.checkSizeError(msgArray)) {
            uavMsg.error = true
            uavMsgArray.add(uavMsg)
            return
        }
        //添加相应信息
        try {
            uavMsg.id = msgArray[0]
            uavMsg.x = msgArray[1].toInt()
            uavMsg.y = msgArray[2].toInt()
            uavMsg.z = msgArray[3].toInt()
            if (msgArray.size == 4) {
                uavMsgArray.add(uavMsg)
                return
            }
            uavMsg._x = msgArray[4].toInt()
            uavMsg._y = msgArray[5].toInt()
            uavMsg._z = msgArray[6].toInt()
            if (!CheckUtil.checkCoordinate(uavMsgArray.last(),uavMsg)){
                error = true
            }
        } catch (e: Exception) {
            //如果封装错误，如信息不正确,String->int失败
            error = true
        }
        uavMsg.error = error
        uavMsgArray.add(uavMsg)
    }

    /**
     * 获取指定ID的信息
     * @param index 指定ID
     * @param listener 信息查询回调接口
     */
    override fun getIndexMsg(index: Int,listener: onInfoHandleFinishListener) {
       try {
            val uavMsg = uavMsgArray[index]
            if (uavMsg.error){
                listener.onErrorMsg(index)
            }else{
                listener.onSuccessMsg(uavMsg,index)
            }
        } catch (e: Exception) {
           listener.onNoFindMsg(index)
        }

    }

    interface onInfoHandleFinishListener{
        fun onSuccessMsg(uavMsg: UavMsg, index: Int)
        fun onErrorMsg(index:Int)
        fun onNoFindMsg(index: Int)
    }

}