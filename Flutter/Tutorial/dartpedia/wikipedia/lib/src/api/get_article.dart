import '../model/article.dart';
import 'utils.dart';

Future<List<Article>> getArticleByTitle(String title) async {
  final Uri url = Uri.https('en.wikipedia.org', '/w/api.php', <String, Object?>{
    'action': 'query',
    'format': 'json',
    'titles': title.trim(),
    'prop': 'extracts',
    'explaintext': '',
  });

  return fetchAndParse(
    url,
    (dynamic json) => Article.listFromJson(json as Map<String, Object?>),
  );
}
