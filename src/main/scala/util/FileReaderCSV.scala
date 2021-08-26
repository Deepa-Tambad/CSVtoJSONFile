package util

import model.{Trainer,Trainers}
import spray.json.{RootJsonFormat, enrichString}
import spray.json.DefaultJsonProtocol._
import scala.io.Source
import scala.util.{Failure, Success, Try}


class FileReaderCSV extends App{


def readCSVFile(path: String): Option[util.List[String]] = {
    val data= Source.fromFile(path)
    Try {
          val list:Iterator[String]=data.getLines()
          val data_List:util.List[String]=
      case Success(source: Source) => {
        return source.next.toString()
      }
      case Failure(exception) => {
        return "Couldn't read the message" +exception.getMessage
      }
    }
  }
  implicit val TrainerJsonFormat: RootJsonFormat[Trainer] = jsonFormat4(Trainer)

  implicit val TrainersJsonFormat: RootJsonFormat[Trainers] = jsonFormat1(Trainers)

  def convertToTrainer(trainer : String) = {
    trainer.parseJson.convertTo[Trainer]
  }
  def convertToTrainers(trainers : String ) = {
    trainers.parseJson.convertTo[Trainers]
  }
  def readJsonFile(jsonSourcePath : String): List[String] = {
    Try(Source.fromFile(jsonSourcePath)) match {
      case Success(source: Source) => {
        val fileContent = source.getLines().toList
        source.close()
        fileContent
      }
      case Failure(exception) => {
        List.empty
      }
    }
  }


}

