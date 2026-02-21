import 'dart:io';
import 'package:logging/logging.dart';

Logger initFileLogger(String name) {
  // Enables logging from child loggers.
  hierarchicalLoggingEnabled = true;

  // Create a logger instance with the provided name.
  final logger = Logger(name);
  final now = DateTime.now();

  // Get the path to the project directeory from the current script.
  final scripFile = File(Platform.script.toFilePath());
  final projectDir = scripFile.parent.parent.path;

  // Create a 'logs' directory if it doesn't exist
  final dir = Directory('$projectDir/logs');
  if (!dir.existsSync()) dir.createSync();

  final logFile = File(
    '${dir.path}/${now.year}_${now.month}_${now.day}_$name.txt',
  );

  // ALL logs everything regardless the severity level
  logger.level = Level.ALL;

  // Listen for log records and write each one to the log file
  logger.onRecord.listen(
    (record) => logFile.writeAsStringSync(
      '[${record.time} - ${record.loggerName}] ${record.level.name}: ${record.message} \n',
      mode: FileMode.append,
    ),
  );

  return logger;
}
