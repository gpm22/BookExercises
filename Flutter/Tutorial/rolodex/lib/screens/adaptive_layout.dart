import 'package:flutter/cupertino.dart';

import 'contact_groups.dart';

const largeScreenMinWidth = 600;

class AdaptiveLayout extends StatefulWidget {
  const AdaptiveLayout({super.key});

  @override
  State<StatefulWidget> createState() => _AdaptiveLayoutState();
}

class _AdaptiveLayoutState extends State<AdaptiveLayout> {
  int selectedListId = 0;

  void _onContactListSelect(int listId) =>
      setState(() => selectedListId = listId);

  @override
  Widget build(BuildContext context) => LayoutBuilder(
    builder: (context, constraints) =>
        constraints.maxWidth > largeScreenMinWidth
        ? _buildLargeScreenLayout()
        : const ContactGroupsPage(),
  );

  Widget _buildLargeScreenLayout() => CupertinoPageScaffold(
    backgroundColor: CupertinoColors.extraLightBackgroundGray,
    child: SafeArea(child: Row(children: [
          // Contact groups list:
          SizedBox(
            width: 320,
            child: Text('Sidebar placeholder'), // Temporary
          ),
          // Divider:
          Container(
            width: 1,
            color: CupertinoColors.separator,
          ),
          // List detail view:
          Expanded(
            child: Text('Details placeholder'), // Temporary
          ),
    ])),
  );
}
