// ignore_for_file: prefer_const_constructors

import 'package:flutter/material.dart';

class LoginScreen extends StatelessWidget {
  const LoginScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Color(0xFFF66F4D),
      body: Stack(
        children:[
           Align(
           alignment: Alignment.topCenter,
           child: (Image(image: AssetImage('assets/Earth.png')))
          ),
          Column(
            children: [
               Container(
                  width: double.infinity,
                  height: MediaQuery.of(context).size.height * 0.2,
                  color: Colors.transparent, // Đặt màu trong suốt để không che phủ nền
                ),
              Container(
                 width: double.infinity, // Chiếm hết chiều rộng của parent
                 height: MediaQuery.of(context).size.height * 0.8,
                 clipBehavior: Clip.antiAlias,
                 decoration: ShapeDecoration(
                  color: Colors.white,
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.only(
                      topLeft: Radius.circular(50),
                      topRight: Radius.circular(50),
                    )
                  ),
                  shadows: const [
                    BoxShadow(
                      color: Color(0x3F000000),
                      blurRadius: 4,
                      offset: Offset(0, 4),
                      spreadRadius: 0,
                    )                 ]
                 ),
                child: Column(
                children: [
                  Expanded(child: SizedBox()),
                  (Image(image: AssetImage('assets/Union.jpg'))),
                  SizedBox(height: 16.0),
                  Text(
                    'TravelPlan',
                    style: TextStyle(
                      color: Color(0xFFF66F4D),
                      fontSize: 48.57,
                      fontFamily: 'Sen',
                      fontWeight: FontWeight.w700,
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.all(30.0),
                    child: TextField(
                      decoration: InputDecoration(
                        labelText: 'Username',
                        hintText: 'Nhập tên người dùng',
                        border: OutlineInputBorder(
                            borderRadius: BorderRadius.circular(10.0), // Đặt độ cong của viền
                          ),
                          focusedBorder: OutlineInputBorder(
                            borderRadius: BorderRadius.circular(10.0),
                            borderSide: BorderSide(
                              color: Colors.blue, // Màu viền khi ô nhập được tập trung
                              width: 2.0, // Độ dày của viền
                           ),
                         ),
                      ),
                    ),
                  ),
                   Padding(
                    padding: const EdgeInsets.fromLTRB(30.0,5.0,30.0,30.0),
                    child: TextField(
                      decoration: InputDecoration(
                        labelText: 'Password',
                        hintText: 'Nhập tên người dùng',
                        border: OutlineInputBorder(
                            borderRadius: BorderRadius.circular(10.0), // Đặt độ cong của viền
                          ),
                          focusedBorder: OutlineInputBorder(
                            borderRadius: BorderRadius.circular(10.0),
                            borderSide: BorderSide(
                              color: Colors.blue, // Màu viền khi ô nhập được tập trung
                              width: 2.0, // Độ dày của viền
                           ),
                         ),
                      ),
                    ),
                  )
                
                ],
                ),
              ),
            ],
          ),
        ] 
      )
    );
  }
}