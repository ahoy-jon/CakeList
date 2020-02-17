import com.oleksandrah.cakes.model.Cakes
import com.oleksandrah.cakes.model
import com.oleksandrah.cakes.CakeServlet
//import com.oleksandrah.cakes.SlickApp

import com.mchange.v2.c3p0.ComboPooledDataSource
import org.slf4j.LoggerFactory
import slick.jdbc.H2Profile.api._

import org.scalatra._
import org.scalatra.LifeCycle
import javax.servlet.ServletContext

class ScalatraBootstrap extends LifeCycle {

  // val logger = LoggerFactory.getLogger(getClass)

  // val cpds = new ComboPooledDataSource
  // logger.info("Created c3p0 connection pool")

  override def init(context: ServletContext) {
    //   val db = Database.forDataSource(cpds, None)   // create the Database object
    //    context.mount(new SlickApp(db), "/*")   // create and mount the Scalatra application

    val cakes = collection.mutable.Map[Integer, Cakes]()
    context mount(new CakeServlet(cakes), "/*")
  }

  //  private def closeDbConnection() {
  //   logger.info("Closing c3po connection pool")
  //   cpds.close
}

// override def destroy(context: ServletContext) {
//  super.destroy(context)
//   closeDbConnection
// }
//}
