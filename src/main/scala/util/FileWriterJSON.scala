package util
import model.{Trainer,Trainers}
import java.io.PrintWriter
import scala.util.{Failure, Success, Try}

class FileWriterJSON {


  def convertCsvToTrainers(file: List[String]): Trainers = {

    val first: List[String] = file.head.split(",").toList

    @tailrec
    def readData(tail: List[String], front: List[String],
                 trainers: List[Trainer] ): List[Trainer] = tail match {
      case Nil => trainers
      case head :: tail => {
        val row_Value: List[String] = head.split(",").toList
        val tuple: List[(String, String)] = front.zip(row_Value)
        val tupleMap: Map[String, String] = tuple.toMap
        readData(tail, front, trainers)
      }
    }

    val tainers = List.empty[Trainer]

    val trainer = Trainers.apply(readData(file.tail, first, tainers))
    trainer
  }

  def CsvToJson(file: List[String]): Trainers = {

    val row = file.tail

    val trainers: List[Trainer] = row.map { it =>
      it.split(",")
    }.map(it => Try(Some.apply(it.apply(0), it.apply(1), it.apply(2), it.apply(3))) match {
      case Success(value) => value
      case Failure(exception) =>
        Some(it.apply(0), it.apply(1), it.apply(2),"")
    }).map(it => {
      val data: (String, String, String, String) = it.get
      val mobileNumber = if (data._4 != null && data._4.nonEmpty) Some.apply(data._4.toLong) else None
      Trainer.apply(data._1.toLong, data._2, data._3, mobileNumber)
    })
    Trainers.apply(trainers)
  }

}


