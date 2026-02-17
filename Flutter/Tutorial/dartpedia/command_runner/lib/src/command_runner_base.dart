import 'dart:async';
import 'dart:collection';
import 'dart:io';

import 'arguments.dart';
import 'exception.dart';

class CommandRunner {
  CommandRunner({this.onOutput, this.onError});

  final Map<String, Command> _commands = <String, Command>{};

  UnmodifiableSetView<Command> get commands =>
      UnmodifiableSetView<Command>(<Command>{..._commands.values});

  FutureOr<void> Function(Object)? onError;

  FutureOr<void> Function(String)? onOutput;

  /// Runs the command-line application logic with given arguments
  Future<void> run(List<String> input) async {
    try {
      final ArgResults results = parse(input);

      if (results.command != null) {
        Object? output = await results.command!.run(results);
        if (onOutput != null) {
          await onOutput!(output.toString());
        } else {
          print(output.toString());
        }
      }
    } on Exception catch (exception) {
      if (onError != null) {
        onError!(exception);
      } else {
        rethrow;
      }
    }
  }

  void addCommand(Command command) {
    _commands[command.name] = command;
    command.runner = this;
  }

  ArgResults parse(List<String> input) {
    var results = ArgResults();
    if (input.isEmpty) return results;

    if (!_commands.containsKey(input.first)) {
      throw ArgumentException(
        'The first word of input must be a command.',
        null,
        input.first,
      );
    }

    results.command = _commands[input.first];
    input = input.sublist(1);

    if (results.command != null &&
        input.isNotEmpty &&
        _commands.containsKey(input.first)) {
      throw ArgumentException(
        'Input can only contain one command. Got ${input.first} and ${results.command!.name}',
        null,
        input.first,
      );
    }

    Map<Option, Object?> inputOptions = {};

    for (int i = 0; i < input.length; i++) {
      if (!input[i].startsWith('-')) {
        if (results.commandArg != null && results.commandArg!.isNotEmpty) {
          throw ArgumentException(
            'Commands can only have up to one argument.',
            results.command!.name,
            input[i],
          );
        }
        results.commandArg = input[i];
        continue;
      }

      var base = _removeDash(input[i]);

      var option = results.command!.options.firstWhere(
        (option) => option.name == base || option.abbr == base,
        orElse: () {
          throw ArgumentException(
            'Unknown option ${input[i]}',
            results.command!.name,
            input[i],
          );
        },
      );

      if (option.type == OptionType.flag) {
        inputOptions[option] = true;
        continue;
      }

      if (option.type != OptionType.option) {
        continue;
      }

      if (i + 1 >= input.length) {
        throw ArgumentException(
          'Option ${option.name} requires an argument',
          results.command!.name,
          option.name,
        );
      }

      if (input[i + 1].startsWith('-')) {
        throw ArgumentException(
          'Option ${option.name} requires an argument, but got another option ${input[i + 1]}',
          results.command!.name,
          option.name,
        );
      }

      var arg = input[i + 1];
      inputOptions[option] = arg;
    }

    results.options = inputOptions;

    return results;
  }

  String _removeDash(String input) {
    if (input.startsWith('--')) {
      return input.substring(2);
    }

    if (input.startsWith('-')) {
      return input.substring(1);
    }

    return input;
  }

  String get usage {
    final exeFile = Platform.script.path.split('/').last;
    return 'Usage: dart bin/$exeFile <command> [commandArg?] [...options?]';
  }
}
