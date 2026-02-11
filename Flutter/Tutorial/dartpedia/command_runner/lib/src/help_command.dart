import 'dart:async';

import 'arguments.dart';

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
    var usage = runner.usage;
    for (var command in runner.commands) {
      usage += '\n ${command.usage}';
    }

    return usage;
  }
}
