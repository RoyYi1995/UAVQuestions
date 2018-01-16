package view

import presenter.IUavPre
import presenter.UavPre
import java.util.*

class MainView:IMainView {

    private var uavPre:IUavPre = UavPre(this)
    private val scanner = Scanner(System.`in`)

    override fun showUavMsg(msg: String) {
        println(msg)
    }

    override fun inputUavIndex() {
        println("Input end , please input the msg id:")
        var msg = scanner.nextLine()
        while (msg!="exit"){
            try {
                uavPre.getIndexDate(msg.toInt())
            }catch (e:Exception){
                println("Please input right index!")
            }
            msg = scanner.nextLine()
        }
    }

    override fun inputUavInfo(){
        println("Please input the Uav info:")
        var msg = scanner.nextLine()
        while (msg != "end"){
            uavPre.submitDate(msg)
            msg = scanner.nextLine()
        }
    }
}