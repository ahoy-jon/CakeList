package com.oleksandrah.cakes.model


import slick.jdbc.H2Profile.api._
import slick.lifted.QueryBase

case class Cake(id: Int, name: String, completed: Boolean)


class Cakes(tag: Tag) extends Table[Cake](tag, "cakes") {
  def id: Rep[Int] = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def name: Rep[String] = column[String]("name")

  def completed: Rep[Boolean] = column[Boolean]("completed")

  def * = (id, name, completed) <> (Cake.tupled, Cake.unapply)
}




object Tables {


  // Table query for the CakeList table, represents all tuples
  val cakes = TableQuery[Cakes]


  // DBIO Action which runs several queries inserting sample data
  val insertCakesData = DBIO.seq(
    cakes += Cake(1, "Tiramisu", completed = false),
    cakes += Cake(2, "Cheesecake New York", completed = true))

  // DBIO Action which creates the schema
  val createSchemaAction = cakes.schema.create

  // DBIO Action which drops the schema
  val dropSchemaAction = cakes.schema.drop


  val createDatabase = DBIO.seq(createSchemaAction, insertCakesData)

}
