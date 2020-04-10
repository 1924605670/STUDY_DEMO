package com.czh.study.traitdemo


trait Equ {
  def isEqu(o: Any): Boolean

  def isNotEqu(o: Any): Boolean = {
    !isEqu(o)
  }

}

class Humen(xname: String, xage: Int) extends Equ {
  val name = xname
  val age = xage

  override def isEqu(o: Any): Boolean = {
    o.isInstanceOf[Humen] && o.asInstanceOf[Humen].name.equals(this.name)
  }
}

object Trait2 {

  def main(args: Array[String]): Unit = {
    val humen = new Humen("zhangsan",10)
    val humen2 = new Humen("zhangsan",11)
    println(humen.isEqu(humen2))
    println(humen.isNotEqu(humen2))
  }


}
