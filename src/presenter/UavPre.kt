package presenter

import bean.UavMsg
import model.UavMod
import view.IMainView

class UavPre(mainView: IMainView) : IUavPre,UavMod.onInfoHandleFinishListener {
    private val mView: IMainView = mainView

    /**
     * 获取对应表示的无人机信息
     */
    override fun getIndexDate(index: Int) {
        UavMod.getIndexMsg(index,this)
    }

    /**
     * 提交无人机信息
     */
    override fun submitDate(info: String) {
        UavMod.addUavInfo(info)
    }

    /**
     * 查询正确信息回调
     */
    override fun onSuccessMsg(uavMsg: UavMsg, index: Int) {
        mView.showUavMsg("${uavMsg.id} $index ${uavMsg.x + uavMsg._x} " +
                "${uavMsg.y + uavMsg._y} ${uavMsg.z + uavMsg._z}")
    }

    /**
     * 信息故障回调
     */
    override fun onErrorMsg(index: Int) {
        mView.showUavMsg("Error $index")
    }

    /**
     * 无法找到信息回调
     */
    override fun onNoFindMsg(index: Int) {
        mView.showUavMsg("Cannot find $index")
    }

}