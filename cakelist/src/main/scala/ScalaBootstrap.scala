import com.oleksandrah.cakes.model.Cakes
import com.oleksandrah.cakes.model
import com.oleksandrah.cakes.CakeServlet


import org.scalatra.LifeCycle
import javax.servlet.ServletContext

class ScalatraBootstrap extends LifeCycle {

  override def init(context: ServletContext) {
    val cakes = collection.mutable.Map[Integer, Cakes]()
    context mount(new CakeServlet(cakes), "/*")
  }
}


