import '../model/search_results.dart';
import 'utils.dart';

Future<SearchResults> search(String searchTerm) async {
  final Uri url = Uri.https('en.wikipedia.org', '/w/api.php', <String, Object?>{
    'action': 'opensearch',
    'format': 'json',
    'search': searchTerm,
  });

  return fetchAndParse(url, (dynamic json) => SearchResults.fromJson(json as List<Object?>));
}
