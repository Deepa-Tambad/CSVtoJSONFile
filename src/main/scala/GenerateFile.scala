import config.ApplicationConfig
import model._
import util.{FileWriterJSON,FileReaderCSV}
import scala.util.{Failure, Success, Try}
object GenerateFile extends App {



  private def CsvFileToJsonFile = {
    val file: List[String] = Try(FileReaderCSV.CsvToJson(ApplicationConfig.config.getString("source_path"))) match {
      case Success(value) => value
      case Failure(exception) => List.empty
    }

    if (file.isEmpty) {
      FileWriterJSON.logErrorFile(ApplicationConfig.config.getString("error_path"), "Couldn't read the CSV File")
    } else {
      FileWriterJSON.convertCsvToTrainers(FileReaderCSV.convertCsvToTrainers(file), ApplicationConfig.config.getString("csv_path"))  
    }
  }
}

