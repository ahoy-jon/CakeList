import com.oleksandrah.cakes.model.{Cake, Cakes, Tables}
import com.oleksandrah.cakes.model
import com.oleksandrah.cakes.CakeServlet
import slick.jdbc.H2Profile

import scala.concurrent.Await
import scala.concurrent.duration.Duration
//import com.oleksandrah.cakes.SlickApp

import com.mchange.v2.c3p0.ComboPooledDataSource
import org.slf4j.LoggerFactory
import slick.jdbc.H2Profile.api._

import org.scalatra._
import org.scalatra.LifeCycle
import javax.servlet.ServletContext



object TestDatabaseApplication {


  def main(args: Array[String]): Unit = {
    println("Hello ! ")

    val db: H2Profile.backend.Database = Database.forConfig("h2mem1")

    def exec[T](action: DBIO[T]): T = Await.result(db.run(action), Duration.Inf)


    exec(Tables.createDatabase)
    println(exec(Tables.cakes.map(_.name).result))


  }
}


class ScalatraBootstrap extends LifeCycle {

  // val logger = LoggerFactory.getLogger(getClass)

  // val cpds = new ComboPooledDataSource
  // logger.info("Created c3p0 connection pool")

  override def init(context: ServletContext) {

    val db: H2Profile.backend.Database = Database.forConfig("h2mem1")

    //   val db = Database.forDataSource(cpds, None)   // create the Database object
    //    context.mount(new SlickApp(db), "/*")   // create and mount the Scalatra application


    Await.ready(db.run(Tables.createDatabase), Duration.Inf)


    context mount(new CakeServlet(db), "/*")
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
