import 'package:flutter/material.dart';

class onboarding1 extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
        color: Colors.white,
        borderRadius: BorderRadius.circular(32),
        boxShadow: [
          BoxShadow(
            color: Color(0x3F000000),
            blurRadius: 40,
            offset: Offset(0, 0),
          )
        ],
      ),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Expanded(child: SizedBox()),
          Text(
            'Welcome to',
            style: TextStyle(
              color: Colors.black,
              fontSize: 30,
              fontFamily: 'Inter',
              fontWeight: FontWeight.w600,
            ),
          ),
          SizedBox(height: 16),
          Image.asset('assets/Union.jpg'),
        
          SizedBox(height: 16),
          Text(
            'TravelPlan',
            style: TextStyle(
              color: Color(0xFFF66F4D),
              fontSize: 48.57,
              fontFamily: 'Sen',
              fontWeight: FontWeight.w700,
            ),
          ),
          Expanded(child: SizedBox()),
          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 20),
            child: Text(
              'Discover the world with ease - plan your adventures, and find the perfect accommodations...',
              textAlign: TextAlign.center,
              style: TextStyle(
                color: Colors.black,
                fontSize: 16,
                fontFamily: 'Inter',
                fontWeight: FontWeight.w400,
              ),
            ),
          ),
          SizedBox(height: 40),
          Row(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.end,
            children: [
              Container(
                width: 25,
                height: 10,
                decoration: BoxDecoration(
                  color: Color(0xFFF66F4D),
                  borderRadius: BorderRadius.circular(12),
                ),
              ),
              SizedBox(width: 5),
              Container(
                width: 10,
                height: 10,
                decoration: BoxDecoration(
                  color: Color(0xFF352555),
                  shape: BoxShape.circle,
                ),
              ),
            ],
          ),
          SizedBox(height: 16),
          Container(
            width: 188,
            height: 67.63,
            // Add your content or widgets here
          ),
        ],
      ),
    );
  }
}
