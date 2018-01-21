package model

import bean.UavMsg
import util.CheckUtil

object UavMod : IUavMod {
    private var uavMsgArray = arrayListOf<UavMsg>()
    private var error = false//是否故障
    /**
     * 检测并添加无人机信息
     * @param msg 信息
     */
    override fun addUavInfo(msg: String) {
        val msgArray = msg.trim().split(" ")
        val uavMsg = UavMsg()

        //如果之前已经故障，则该信息故障
        //判断每次输入id是否相同
        //检测开头是否为4字符，后续是否为7字符
        //检测id是否符合字母+数字的组合
        if (error || CheckUtil.checkUavIdError(uavMsgArray, msgArray[0])
                || CheckUtil.checkSizeError(msgArray) || CheckUtil.checkIdFormatError(msgArray[0])) {
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
            if (!CheckUtil.checkCoordinate(uavMsgArray.last(), uavMsg)) {
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
    override fun getIndexMsg(index: Int, listener: OnInfoHandleFinishListener) {
        try {
            val uavMsg = uavMsgArray[index]
            if (uavMsg.error) {
                listener.onErrorMsg(index)
            } else {
                listener.onSuccessMsg(uavMsg, index)
            }
        } catch (e: Exception) {
            listener.onNoFindMsg(index)
        }

    }

    interface OnInfoHandleFinishListener {
        fun onSuccessMsg(uavMsg: UavMsg, index: Int)
        fun onErrorMsg(index: Int)
        fun onNoFindMsg(index: Int)
    }

}