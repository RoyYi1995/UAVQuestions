package view

import presenter.IUavPre
import presenter.UavPre
import java.io.File
import java.io.FileNotFoundException
import java.util.*

class MainView : IMainView {


    private val uavPre: IUavPre = UavPre(this)
    private val scanner = Scanner(System.`in`)

    /**
     * 输入无人机信息文本地址
     */
    override fun inputUavInfoWithPath() {
        println("Please input the UavMsg file path:")
        try {
            val file = File(scanner.nextLine())
            println(file.absoluteFile)
            file.forEachLine {
                println(it)
                uavPre.submitDate(it)
            }
        }catch (e:FileNotFoundException){
            println("Please input right file path!!!")
            inputUavInfo()
        }
    }
    /**
     * 输入无人机信息
     */
    override fun inputUavInfo() {
        println("Please input uav info:")
        //逐行输入无人机信息内容
        var msg = scanner.nextLine()
        //以"end"为无人机信息输入结束
        while (msg != "end") {
            uavPre.submitDate(msg)
            msg = scanner.nextLine()
        }
    }

    /**
     * 输入指定无人机信息编号
     */
    override fun inputUavIndex() {
        println("Input end , please input the msg id:")
        var msg = scanner.nextLine()
        //以"exit"为程序退出
        while (msg != "exit") {
            try {
                uavPre.getIndexDate(msg.toInt())
            } catch (e: Exception) {
                println("Please input right index!")
            }
            msg = scanner.nextLine()
        }
        scanner.close()
    }

    /**
     * 界面显示指定无人机信息
     */
    override fun showUavMsg(msg: String) {
        println(msg)
    }
}