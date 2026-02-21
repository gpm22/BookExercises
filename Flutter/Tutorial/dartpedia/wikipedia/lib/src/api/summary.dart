import '../model/summary.dart';
import 'utils.dart';

final Uri urlRandom = Uri.https(
  'en.wikipedia.org',
  '/api/rest_v1/page/random/summary',
);

Summary fromJson(dynamic json) => Summary.fromJson(json as Map<String, Object?>);

Future<Summary> getRandomArticleSummary() async {
  return fetchAndParse(urlRandom, fromJson);
}

Future<Summary> getArticleSummaryByTitle(String articleTitle) {
  final Uri url = Uri.https(
    'en.wikipedia.org',
    '/api/rest_v1/page/summary/$articleTitle',
  );

  return fetchAndParse(url, fromJson);
}
