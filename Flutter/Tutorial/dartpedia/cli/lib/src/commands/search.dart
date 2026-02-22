import 'dart:async';

import 'package:command_runner/command_runner.dart';
import 'package:wikipedia/wikipedia.dart';

import 'logged_command.dart';

class SearchCommand extends LoggedCommand {
  SearchCommand({required super.logger}) {
    addFlag(
      'im-feeling-lucky',
      help:
          'If true, prints the summary of the top article that the search returns',
    );
  }

  @override
  String get description => 'Search for Wikipedia articles';

  @override
  String get name => 'search';

  @override
  String get valueHelp => 'STRING';

  @override
  String get help =>
      'Prints a list of links to Wikipedia articles that match the given term';

  @override
  FutureOr<Object?> baseRun(ArgResults args) async {
    if (requiresArgument &&
        (args.commandArg == null || args.commandArg!.isEmpty)) {
      return 'Please include a search term';
    }

    final SearchResults results = await search(args.commandArg!);

    if (results.results.isEmpty) {
      return 'No results found for ${args.commandArg}';
    }

    final buffer = StringBuffer('Search results:');

    if (args.flag('im-feeling-lucky')) {
      final title = results.results.first.title;
      final Summary article = await getArticleSummaryByTitle(title);
      buffer.writeln('Lucky you!');
      buffer.writeln(article.titles.normalized.titleText);
      if (article.description != null) {
        buffer.writeln(article.description);
      }

      buffer.writeln(article.extract);
      buffer.writeln();
      buffer.writeln('All results:');
    }

    results.results
        .map((result) => '${result.title} - ${result.url}')
        .forEach(buffer.writeln);

    return buffer.toString();
  }
}
