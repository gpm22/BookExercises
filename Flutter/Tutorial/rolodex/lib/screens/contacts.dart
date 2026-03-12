import 'package:flutter/cupertino.dart';

class ContactListsPage extends StatelessWidget {
  const ContactListsPage({super.key, required this.listId});
  final int listId;

  @override
  Widget build(BuildContext context) => const CupertinoPageScaffold(
    backgroundColor: CupertinoColors.extraLightBackgroundGray,
    child: Center(
      child: Text('List of contactrs will be here'),
    ),
  );
}
