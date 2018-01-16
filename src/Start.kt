import view.IMainView
import view.MainView

fun main(args: Array<String>) {
    val mView:IMainView = MainView()
    mView.inputUavInfo()
    mView.inputUavIndex()
}