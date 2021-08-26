package util

import model.{Trainer,Trainers}
import spray.json.{RootJsonFormat, enrichString}
import spray.json.DefaultJsonProtocol._
import scala.io.Source
import scala.util.{Failure, Success, Try}


class FileReaderCSV extends App{

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

    val trainers = List.empty[Trainer]

    val trainer = Trainers.apply(readData(file.tail, first, tainers))
    trainer
  }
  def CsvToJson(file: List[String]): Trainers = {

    val row = file.tail

    val trainers: List[Trainer] = row.map { train =>
      train.split(",")
    }.map(train => Try(Some.apply(train.apply(0), train.apply(1), train.apply(2), train.apply(3))) match {
      case Success(value) => value
      case Failure(exception) =>
        Some(train.apply(0), train.apply(1), train.apply(2),"")
    }).map(train => {
      val data: (String, String, String, String) = train.get
      val mobile = if (data._4 != null && data._4.nonEmpty) Some.apply(data._4.toLong) else None
      Trainer.apply(data._1.toLong, data._2, data._3, mobile)
    })
    Trainers.apply(trainers)
  }
  
  implicit val TrainerJsonFormat: RootJsonFormat[Trainer] = jsonFormat4(Trainer)

  implicit val TrainersJsonFormat: RootJsonFormat[Trainers] = jsonFormat1(Trainers)

  def convertToTrainer(trainer : String) = {
    trainer.parseJson.convertTo[Trainer]
  }
  def convertToTrainers(trainers : String ) = {
    trainers.parseJson.convertTo[Trainers]
  }
}
