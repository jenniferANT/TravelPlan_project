// ignore_for_file: prefer_const_constructors

import 'package:flutter/material.dart';
import 'screens/onboarding1.dart';
import 'screens/onboarding2.dart';
import 'screens/login.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        body: Center(
          child: LoginScreen(),
        ),
      ),
    );
  }
}

