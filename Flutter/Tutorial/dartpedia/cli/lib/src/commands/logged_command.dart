import 'dart:async';
import 'dart:io';

import 'package:command_runner/command_runner.dart';
import 'package:logging/logging.dart';

abstract class LoggedCommand extends Command {
  LoggedCommand({required this.logger});

  final Logger logger;

  FutureOr<Object?> baseRun(ArgResults args);

  @override
  FutureOr<Object?> run(ArgResults args) async {
    try {
      return baseRun(args);
    } on HttpException catch (e) {
      logger
        ..warning(e.message)
        ..warning(e.uri)
        ..info(usage);
      return e.message;
    } on FormatException catch (e) {
      logger
        ..warning(e.message)
        ..warning(e.source)
        ..info(usage);

      return e.message;
    }
  }
}
