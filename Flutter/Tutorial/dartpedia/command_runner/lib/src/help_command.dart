import 'dart:async';

import 'package:command_runner/command_runner.dart';

// Prints program and argument usage.
//
// When given a command as an argument, it prints the usage of
// that command only, including its options and other details.
// When the flag 'verbose' is set, it prints options and details for all commands.
//
// This command isn't automatically added to CommandRunner instances.
// Packages users should add it themselves with [CommandRunner.addCommand],
// or create their own command that prints usage.

class HelpCommand extends Command {
  HelpCommand() {
    addFlag(
      'verbose',
      abbr: 'v',
      help: 'When true, this command will print each command and its options.',
    );
    addOption(
      'command',
      abbr: 'c',
      help:
          "Whe a command is passed as an argument, prints only that command's verbose usage.",
    );
  }

  @override
  String get name => 'help';

  @override
  String get description => 'Prints usage information to the command line.';

  @override
  String? get help => 'Prints this usage information';

  @override
  FutureOr<Object?> run(ArgResults args) async {
    final buffer = StringBuffer();
    buffer.writeln(runner.usage.titleText);

    if (args.flag('verbose')) {
      runner.commands
          .map((c) => _renderCommandVerbose(c))
          .forEach(buffer.write);

      return buffer.toString();
    }

    if (args.hasOption('commnad')) {
      var (:option, :input) = args.getOption('command');

      var cmd = runner.commands.firstWhere(
        (command) => command.name == input,
        orElse: () => throw ArgumentException(
          'Input ${args.commandArg} is not a known command.',
        ),
      );

      return _renderCommandVerbose(cmd);
    }

    runner.commands.map((c) => c.usage).forEach(buffer.writeln);

    return buffer.toString();
  }

  String _renderCommandVerbose(Command cmd) {
    final indent = ' ' * 10;
    final buffer = StringBuffer();

    buffer.writeln(cmd.usage.instructionText);
    buffer.writeln('$indent ${cmd.help}');

    if (cmd.valueHelp != null) {
      buffer.writeln(
        '$indent [Argument] Required? ${cmd.requiresArgument}, Type: ${cmd.valueHelp}, Default: ${cmd.defaultValue ?? 'none'}',
      );
    }

    buffer.writeln('$indent Options:');

    cmd.options
        .map((option) => '$indent ${option.usage}')
        .forEach(buffer.writeln);

    return buffer.toString();
  }
}
