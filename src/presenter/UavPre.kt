package presenter

import model.IUavMod
import model.UavMod
import view.IMainView

class UavPre(mainView: IMainView) : IUavPre {
    private val uavMod: IUavMod = UavMod()
    private var mView: IMainView = mainView

    override fun getIndexDate(index: Int) {
        val msg = uavMod.getIndexMsg(index)
        mView.showUavMsg(msg)
    }

    override fun submitDate(info: String) {
        uavMod.addUavInfo(info)
    }

}