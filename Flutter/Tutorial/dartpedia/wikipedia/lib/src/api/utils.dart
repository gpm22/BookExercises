import 'dart:convert';
import 'dart:io';

import 'package:http/http.dart' as http;

/// Generic HTTP GET function that fetches JSON and converts it using a factory
///
/// Example usage:
/// ```dart
/// final summary = await fetchAndParse(
///   Uri.parse('https://api.example.com/summary'),
///   Summary.fromJson,
/// );
///
/// final user = await fetchAndParse(
///   Uri.parse('https://api.example.com/user/123'),
///   User.fromJson,
/// );
/// ```
Future<T> fetchAndParse<T>(
  Uri url,
  T Function(dynamic) fromJson, 
) async {
  final httpClient = http.Client();

  try {
    final response = await httpClient.get(url);

    if (response.statusCode != 200) {
      throw HttpException(
        'GET $url failed → status: ${response.statusCode}, body: ${response.body}',
        uri: url,
      );
    }

    return fromJson(jsonDecode(response.body));
  } finally {
    httpClient.close();
  }
}
