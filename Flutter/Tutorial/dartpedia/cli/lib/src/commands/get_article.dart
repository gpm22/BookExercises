import 'dart:async';

import 'package:command_runner/command_runner.dart';
import 'package:wikipedia/wikipedia.dart';

import 'logged_command.dart';

class GetArticleCommand extends LoggedCommand {
  GetArticleCommand({required super.logger});

  @override
  String get description => 'Read an article from wikipedia';

  @override
  String get name => 'article';

  @override
  String get help => 'Gets an article by exact canonical wikipedia title.';

  @override
  String get defaultValue => 'cat';

  @override
  String get valueHelp => 'STRING';

  @override
  FutureOr<Object?> baseRun(ArgResults args) async {
    var title = args.commandArg ?? defaultValue;
    final List<Article> articles = await getArticleByTitle(title);

    if (articles.isEmpty) {
      return 'No results found for $title';
    }

    final article = articles.first;

    final buffer = StringBuffer('\n=== ${article.title.titleText} ===\n\n');
    buffer.write(article.extract.split(' ').take(500).join(' '));
    return buffer.toString();
  }
}
